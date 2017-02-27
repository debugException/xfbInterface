package cn.tyiti.xfb.service.blacklist.impl;   

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xpath.internal.axes.NodeSequence;

import wcf.client.IInternal;

import cn.emagsoftware.utils.SeqUtil;
import cn.emagsoftware.xfb.dao.CreditDao;
import cn.tyiti.xfb.bojo.blacklist.BlistInfo;
import cn.tyiti.xfb.bojo.blacklist.SybadInfo;
import cn.tyiti.xfb.constant.GlobalConstant;
import cn.tyiti.xfb.dao.blacklist.IsybadInfoManageDao;
import cn.tyiti.xfb.dao.blacklist.impl.BlistInfoManageDao;
import cn.tyiti.xfb.service.blacklist.IsybadInfoService;
import cn.tyiti.xfb.utils.DocumentUtil;

/** 
 * 创建时间：2015-11-24 下午9:54:26  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：SybadInfoService.java  
 * 类说明：  
 */
@Service("sybadInfoService") 
public class SybadInfoService implements IsybadInfoService {

	@Autowired
	private IsybadInfoManageDao sybadInfoManageDao ;
	
	@Autowired
	private BlistInfoManageDao blistInfoManageDao ;
	
	@Autowired
	private CreditDao creditDao ;
	
	
	/**
	 * 查询成功状态返回码
	 * */
	public final static String QUERY_SUCCESS = "0";
	
    @Autowired
    private SeqUtil seqUtil;
	/**
	 *<p>Title:insertSybadInfo</p>
	 *<p>Description:</p> resultDoc document文档，  serviceCode接口名称， name姓名， idcode 身份证号， userId， loginName 手机号
	 *@param params  
	 *@throws Exception
	 *@see cn.tyiti.xfb.service.blacklist.IsybadInfoService#insertSybadInfo(java.util.Map)
	 */
	@Override
	public void insertSybadInfo(Map<String, Object> params) throws Exception {

		Document resultDoc = (Document) params.get("resultDoc");//document文档
		String serviceCode = (String) params.get("serviceCode");//接口名称
		String name = (String) params.get("name");//姓名
		String idcode = (String) params.get("idcode");// 身份证号
		String userId = (String) params.get("userId");//
		String loginName = (String) params.get("loginName");//手机号
		String msgId = (seqUtil
				.getNextValueBySeqName(GlobalConstant.GET_T_BLIST_MESSAGE_SEQ)
				.toString());

		String status = DocumentUtil.getStrByNodePath(resultDoc,
				"/DATA/MESSAGE/STATUS");
		String value = DocumentUtil.getStrByNodePath(resultDoc,
				"/DATA/MESSAGE/VALUE");

		BlistInfo blistInfo = new BlistInfo();

		blistInfo.setId(msgId);
		blistInfo.setName(name);
		blistInfo.setUserId(userId);
		blistInfo.setLoginName(loginName);
		blistInfo.setIdcode(idcode);
		blistInfo.setStatus(status);
		blistInfo.setQueryInterface(serviceCode);
		blistInfo.setValue(value);
		blistInfoManageDao.insert(blistInfo);

		String resultsENText = "";

		if (QUERY_SUCCESS.equals(status)) {
			resultsENText = DocumentUtil.getDecryptStrByNodePath(resultDoc,
					"/DATA/RESULTS");

			if (null != resultsENText && !"".equals(resultsENText)) {

				Document resultDoc2 = DocumentHelper.parseText("<RESULTS>"
						+ resultsENText + "</RESULTS>");
				List<Node> nodes = resultDoc2.selectNodes("/RESULTS/RESULT");
				Element elm1 = (Element) nodes.get(0);
				Element badinfos = elm1.element("BADINFOS");

				List<Element> badinfoElements = badinfos.elements();
				if(badinfoElements.size()>0){
					Map  map = new HashMap();
					map.put("modifyField", "sybadinfo");
					 //map.put("userId", userId);
					 map.put("cardNumber", idcode);
					 map.put("creditCode", msgId);
					creditDao.updateTrxNo(map);
				}

				for (int i = 0; i < badinfoElements.size(); i++) {
					
					Element elm = badinfoElements.get(i);

					SybadInfo sybadInfo = new SybadInfo();

					Element match1 = elm1.element("MOBILE");
					sybadInfo.setMobile(match1.getText());

					sybadInfo.setMsgId(msgId);
					sybadInfo.setName(name);
					sybadInfo.setIdcode(idcode);
					sybadInfo.setUserId(userId);
					sybadInfo.setLoginName(loginName);
					sybadInfo.setMobile(loginName);

					Element match = elm.element("MATCH");
					sybadInfo.setBmatch(match.getText());

					Element reason = elm.element("REASON");
					sybadInfo.setReason(reason.getText());

					Element reason_description = elm
							.element("REASON_DESCRIPTION");
					sybadInfo.setReason_description(reason_description
							.getText());

					Element create_date_type = elm.element("CREATE_DATE_TYPE");
					sybadInfo.setCreate_date_type(create_date_type.getText());

					Element amount_type = elm.element("AMOUNT_TYPE");
					sybadInfo.setAmount_type(amount_type.getText());

					Element over_due_type = elm.element("OVER_DUE_TYPE");
					sybadInfo.setOver_due_type(over_due_type.getText());

					Element legal_status = elm.element("LEGAL_STATUS");
					sybadInfo.setLegal_status(legal_status.getText());

					sybadInfoManageDao.insert(sybadInfo);
				}
			}
		}
	}
}