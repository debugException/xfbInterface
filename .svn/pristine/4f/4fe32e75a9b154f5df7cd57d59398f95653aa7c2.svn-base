package cn.tyiti.xfb.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.bean.XFBResponseBean;
import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.tyiti.xfb.bojo.BankCardInfo;
import cn.tyiti.xfb.constant.BankInfoConstant;
import cn.tyiti.xfb.service.BankCardInfoService;

@Controller
@RequestMapping(value = "/bankCardInfo")
public class BankCardInfoController extends BaseController{
	
	@Autowired
	private BankCardInfoService bankCardInfoService;
	
	@RequestMapping(value = "/saveBankCardInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveBankCardInfo(Map<String, String> model) throws UnsupportedEncodingException {
	 	log.info("提升额度-银行卡操作开始");
    	XFBResponseBean response = new XFBResponseBean();
        response.setResultCode(BankInfoConstant.ERROR_CODE_1000);
        try {	
        	BankCardInfo bankCardInfo = super.getParamConvertEntity(BankCardInfo.class);
        	Integer bankFlag = bankCardInfoService.saveBankCardInfo(bankCardInfo);
        	System.out.println(bankFlag);
        } catch (Exception ex) {
        	//ex.printStackTrace();
            log.error("提升额度-银行卡操作失败", ex);
            response.setResultCode(BankInfoConstant.ERROR_CODE_2000);
        }
        log.info("提升额度-银行卡操作结束");
	    response.setResultMessage(BankInfoConstant.ERROR_MESSAGE.get(response.getResultCode()));
	    model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
	    log.debug("BillStageController.billList.response=="+JsonUtils.getJSONString(response));
        return RET_JSP;
    }
	
	
}
