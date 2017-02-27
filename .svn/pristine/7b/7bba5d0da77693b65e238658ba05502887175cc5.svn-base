/**
 * @(#)PromoteAmountController.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.tyiti.xfb.bojo.PromoteAmount;
import cn.tyiti.xfb.service.PromoteAmountService;
import cn.tyiti.xfb.utils.ResponseUtil;


/**
 * 提升额度-获取数据Controller.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Controller
@RequestMapping(value = "/amount")
public class PromoteAmountController extends BaseController {
	@Autowired
	private PromoteAmountService promoteAmountService;
	/**
     * 获取数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getStateInfo", method = {RequestMethod.POST})
    public String getStateInfo(Map<String, String> model) {
    	PromoteAmount promoteAmount = this.getParamConvertEntity(PromoteAmount.class);
        try {
            Integer userId = this.getUserId();
            promoteAmount = promoteAmountService.getStateInfo(userId);	
    		//成功设置
            promoteAmount.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
                    .get(Constant.SUCCESS_CODE));
        } catch (Exception ex) {
            log.error("CustomerController.getStateInfo", ex);
            promoteAmount.setCodeAndMsg(Constant.ERROR_CODE_9999, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
    	model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(promoteAmount));
        return RET_JSP;
    }
}
