package cn.tyiti.xfb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.tyiti.xfb.bojo.PayRecode;
import cn.tyiti.xfb.dto.PayRecodeRsp;
import cn.tyiti.xfb.service.IPayRecodeService;

/**
 * 
 * 类名称：PayRecodeController <br/>
 * 类描述：接口支付流水控制层类  <br/>
 * @date 2015年10月27日10:47:42 <br/>
 * @author SHENWU
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/payRecode")
public class PayRecodeController extends BaseController {
	
	@Autowired
	private IPayRecodeService payRecodeService;

	/**
	 * 获取支付流水
	 * @param model
	 * @author shenwu
	 * @return RET_JSP
	 */
	@RequestMapping(value = "/getPayRecode", method = {RequestMethod.GET, RequestMethod.POST})
    public String getVersion(Map<String, String> model) {
		log.debug("--------getPayRecode.do>>>>>获取支付流水------");
		PayRecodeRsp payRecode = new PayRecodeRsp();
		try {
			String id = request.getParameter("billId");
			PayRecode pr = this.getParamConvertEntity(PayRecode.class);
			BillStage bs = new BillStage();
			bs.setId(Integer.valueOf(id));
			bs = payRecodeService.checkIsBillId(bs);
			if(bs.getBillNo() != null || "".equals(bs.getBillNo()) || "undefined".equals(bs.getBillNo()) ){
				PayRecode _pr = new PayRecode();
				_pr = payRecodeService.savePayRecode(pr, bs);
				if("".equals(_pr) || _pr != null || "undefined".equals(_pr)){
					payRecode.setPayRecode(_pr.getTradeNo());
					payRecode.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
		                    .get(Constant.SUCCESS_CODE));	//成功设置
				}else {
					payRecode.setCodeAndMsg(Constant.ERROR_CODE_9999, Constant.ERROR_MESSAGE
							.get(Constant.ERROR_CODE_9999));
				}
			}else {
				payRecode.setCodeAndMsg(Constant.ERROR_CODE_9994, Constant.ERROR_MESSAGE
						.get(Constant.ERROR_CODE_9994));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("支付流水操作异常" + e.getMessage(), e);
			payRecode.setCodeAndMsg(Constant.ERROR_CODE_9999, Constant.ERROR_MESSAGE
					.get(Constant.ERROR_CODE_9999));
		}
		model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(payRecode));
		return RET_JSP;
	}
	
}
