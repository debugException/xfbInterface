package cn.emagsoftware.xfb.service.impl;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.emagsoftware.common.JsonSerializer;
import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.service.BaseService;
import cn.emagsoftware.utils.CommonUtils;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.dao.CreditDao;
import cn.emagsoftware.xfb.pojo.CollectInfo;
import cn.emagsoftware.xfb.pojo.CreditInfo;
import cn.emagsoftware.xfb.pojo.LoanInfo;
import cn.emagsoftware.xfb.pojo.LoanInfoBack;
import cn.emagsoftware.xfb.pojo.Pkg1001;
import cn.emagsoftware.xfb.pojo.PkgHeader;
import cn.emagsoftware.xfb.service.CreditService;

@Service("creditService")
public class CreditServiceImpl extends BaseService implements CreditService {

	@Autowired
	private CreditDao creditDao;
	
	//查询个人征信
	@Override
	public void queryCreditInfo(String userId,String userName,String cardNumber) throws Exception {

    	BaseRspBean rsp = new BaseRspBean();
    	Pkg1001 pkg1001 = new Pkg1001();
    	CreditInfo creditInfo = new CreditInfo();
    	
    	//获取机构代码
    	String companyCode = CommonUtils.getPropertiesValue("config", "agency_code");
    	//数据服务地址
    	String creditUrl = CommonUtils.getPropertiesValue("config", "credit_url"); 
    	
    	if (StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(cardNumber)) {
    		creditInfo.setUserName(userName);
    		creditInfo.setCardNumber(cardNumber);
    		
    		pkg1001.setRealName(userName);
    		pkg1001.setIdCard(cardNumber);
    		String msgBody = JsonSerializer.serializer(pkg1001);
    		
    		PkgHeader reqPkg = new PkgHeader();
    		reqPkg.setVersion("01");			//默认01
    		reqPkg.setCustNo(companyCode);				//公司代码
    		reqPkg.setEncode("01");			//01.UTF8 02.GBK
    		reqPkg.setTrxCode("1001");			//报文编号 默认四位 例:0001
    		reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
    		reqPkg.setMsgType("01");			//01.JSON 02.XML 03.Protobuf
    		reqPkg.setMsgBody(msgBody);			//报文主体
    		
    		String pkgStr = reqPkg.toPkgStr("UTF-8");
    		
    		PkgHeader pkgHeader = new PkgHeader();
    		pkgHeader.parseFromString(pkgStr);
    		
    		CloseableHttpClient httpclient = createSSLClientDefault();
    		HttpPost post = new HttpPost(creditUrl);
    		ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));
    		post.setEntity(reqEntity);
    		
    		CloseableHttpResponse response;
    		String result = "";
    		try {
    			response = httpclient.execute(post);
    			HttpEntity rspEntity = response.getEntity();

    		    if (rspEntity != null) {
    		    	result = EntityUtils.toString(rspEntity);
    		    }
    		    pkgHeader.parseFromString(result);
    		    String trxNoJson = pkgHeader.getMsgBody();
    		    //输出返回信息
    		    String trxNo = (String) JsonUtils.getMapFromJson(trxNoJson).get("trxNo");
    		    creditInfo.setTrxNo(trxNo);
    		    creditInfo.setUserId(userId);
    		    log.info("trxNo=====>>>>"+trxNo);
    		    creditDao.addCreditInfo(creditInfo);
    		    log.info("保存交易代码成功！");
    		    response.close();
    		    rsp.setResultCode(Constant.SUCCESS_CODE);
    			rsp.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.SUCCESS_CODE));
    		} catch (Exception e) {
    			e.printStackTrace();
    			rsp.setResultCode(Constant.ERROR_CODE_9999);
    			rsp.setResultMessage(Constant.ERROR_MESSAGE.get("ERROR_CODE_9999"));
    		}
		}else {
			rsp.setResultCode(Constant.ERROR_CODE_9997);
			rsp.setResultMessage(Constant.ERROR_MESSAGE.get("ERROR_CODE_9997"));
		}
	
	}

	//保存查询结果集
	@Override
	public void addLoanInfo(LoanInfo loanInfo) throws Exception {
		creditDao.saveLoanInfo(loanInfo);
	}

	//查询用户id
	@Override
	public String getUserIdByTrxNo(String trxNo) throws Exception {
		return creditDao.getUserIdByTrxNo(trxNo);
	}
	//查询用户
	@Override
	public String getCardNumberByTrxNo(String trxNo) throws Exception {
		return creditDao.getCardNumberByTrxNo(trxNo);
	}

	//修改黑名单表标识位trxNo
	@Override
	public int updateTrxNo(Map<String, String> map) throws Exception {
		return creditDao.updateTrxNo(map);
	}
	
	private  static CloseableHttpClient createSSLClientDefault(){
		try {
			 SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			 //信任所有
			     public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
			         return true;
			     }
			 }).build();
			 SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			 return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		 } catch (KeyManagementException e) {
		     e.printStackTrace();
		 } catch (NoSuchAlgorithmException e) {
		     e.printStackTrace();
		 } catch (KeyStoreException e) {
		     e.printStackTrace();
		 }
		 return  HttpClients.createDefault();
	}

	//收集本地数据库返回给91平台
	@Override
	public List<LoanInfoBack> queryLoanInfos(Map<String, String> map) throws Exception {
		List<LoanInfoBack> loanInfos = new ArrayList<LoanInfoBack>();
		List<CollectInfo> collectInfos = new ArrayList<CollectInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String companyCode = CommonUtils.getPropertiesValue("config", "agency_code");
		 
		collectInfos = creditDao.queryLoanInfos(map);
		for (CollectInfo collectInfo : collectInfos) {
			LoanInfoBack loanInfo = new LoanInfoBack();
			String type = collectInfo.getType(); // 会员类型,
			String status = collectInfo.getStatus(); // 借款状态,
			String credit_sum = collectInfo.getCredit_sum(); // 授信金额,
			String verify_state = collectInfo.getVerify_state(); // 审批状态,
			String m_verify_state = collectInfo.getM_verify_state(); // 会员审批,
			String j_verify_state = collectInfo.getJ_verify_state(); // 工作审批,
			String c_verify_state = collectInfo.getC_verify_state(); // 联系人审批,
			String s_verify_state = collectInfo.getS_verify_state(); // 学生审批,
			String use_limit = collectInfo.getUse_limit(); // 借款金额,
			String create_time = collectInfo.getCreate_time(); // 合同日期,
			String stage_plan = collectInfo.getStage_plan(); // 批贷期数,
			String order_status = collectInfo.getOrder_status(); // 还款状态,
			String overdue_day = collectInfo.getOverdue_day(); // 逾期天数,
			String  arrearsAmount = collectInfo.getArrearsAmount();// 欠款金额 
		 
		    loanInfo.setBorrowType((short) 1); //借款类型
		    /**
		     * 借款状态
		     */
		    if ("2".equals(status)||"3".equals(status)) {  //拒贷
				loanInfo.setBorrowState((short) 1);
			}else {
				if (credit_sum!=null) {
					Double creditDouble = new Double(credit_sum);
					int creditNum = (int) creditDouble.doubleValue();
					if (creditNum>0) {  //批贷已放款
						loanInfo.setBorrowState((short) 2);
					}else if (creditNum==0) {  //批贷未放款
						if ("A4".equals(m_verify_state)&&"A4".equals(j_verify_state)&&"A4".equals(c_verify_state)&&"A2".equals(type)) {
							loanInfo.setBorrowState((short) 3); 
						}if ("A4".equals(m_verify_state)&&"A2".equals(type)&&"A4".equals(c_verify_state)&&"A4".equals(s_verify_state)) {
							loanInfo.setBorrowState((short) 3); 
						}
					}else {
						if ("A1".equals(m_verify_state)) {  //借款人放弃申请
							loanInfo.setBorrowState((short) 4);
						}
						if ("A1".equals(verify_state)) {  //审核中
							loanInfo.setBorrowState((short) 5); 
						}
					}
				}
			}
		    /**
		     * 借款金额
		     */
		    if (use_limit!=null) {
		    	Double  borrowAmount = new Double(use_limit);
		    	if (borrowAmount>=0 && borrowAmount<10000) {
					if (borrowAmount<1000) {
						loanInfo.setBorrowAmount((short) -7);
					}else if (borrowAmount>=1000 && borrowAmount<2000) {
						loanInfo.setBorrowAmount((short) -6);
					}else if (borrowAmount>=2000 && borrowAmount<3000) {
						loanInfo.setBorrowAmount((short) -5);
					}else if (borrowAmount>=3000 && borrowAmount<4000) {
						loanInfo.setBorrowAmount((short) -4);
					}else if (borrowAmount>=4000 && borrowAmount<6000) {
						loanInfo.setBorrowAmount((short) -3);
					}else if (borrowAmount>=6000 && borrowAmount<8000) {
						loanInfo.setBorrowAmount((short) -2);
					}else if (borrowAmount>=8000 && borrowAmount<10000) {
						loanInfo.setBorrowAmount((short) -1);
					}
				}else {
					int borrowCount = (int) (borrowAmount/20000+1);
					loanInfo.setBorrowAmount((short) borrowCount);
				}
			}
		  	/**
		  	 *  合同日期
		  	 */
		    if (create_time!=null) {
		    	Date date=sdf.parse(create_time);
		    	loanInfo.setContractDate(date); 
			}
		    /**
		     * 批贷期数
		     */
		    if (stage_plan!=null) {
		    	loanInfo.setLoanPeriod(new Short(stage_plan)); 
			}
		    /**
		     * 还款状态
		     */
		    if ("9".equals(order_status)||"2".equals(order_status)) {
		    	loanInfo.setRepayState((short) 1);  //正常
		    }else if ("0".equals(order_status)||"1".equals(order_status)||"3".equals(order_status)
		    		||"4".equals(order_status)||"5".equals(order_status)||"6".equals(order_status)) {
				loanInfo.setRepayState((short) 0);  //未知
			}else {
				int day = Integer.parseInt(overdue_day);
				if (day>0) {
					int count = day/30+1;
					if (count==1) {
						loanInfo.setRepayState((short)2);  //M1
					}else if (count==2) {
						loanInfo.setRepayState((short) 3); //M2
					}else if (count==3) {
						loanInfo.setRepayState((short) 4); //M3
					}else if (count==4) {
						loanInfo.setRepayState((short) 5); //M4
					}else if (count==5) {
						loanInfo.setRepayState((short) 6); //M5
					}else if (count==6) {
						loanInfo.setRepayState((short) 7); //M6
					}else if (count>6) {
						loanInfo.setRepayState((short) 8); //M6+
					}
				}
			}
		    /**
		     * 欠款金额
		     */
		    if (arrearsAmount!=null) {
		    	Double  amount = new Double(arrearsAmount);
		    	amount = amount*100000;
		    	loanInfo.setArrearsAmount( (long) Math.round(amount)); 
			}
		 
		    /**
		     * 公司代码
		     */
		    loanInfo.setCompanyCode(companyCode);  
			loanInfos.add(loanInfo);
		}
		return loanInfos;
	}


}
