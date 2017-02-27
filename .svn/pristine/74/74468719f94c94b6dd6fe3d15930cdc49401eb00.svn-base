/**
 * @(#)MemberInfoController.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.tyiti.xfb.service.SyncDataBaseService;


/**
 * 同步用户数据到市场版Controller.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Controller
@RequestMapping(value = "/syncDataBase")
public class SyncDataBaseController extends BaseController {
	@Autowired
	private SyncDataBaseService syncDataBaseService;
	/**
     * 同步用户数据
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/execute", method = {RequestMethod.POST})
    public String execute(Map<String, String> model) {
    	BaseRspBean response = new BaseRspBean();
        try {
            syncDataBaseService.toProcessData();
    		//成功设置
            response.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
                    .get(Constant.SUCCESS_CODE));
        } catch (Exception ex) {
            log.error("SyncDataBaseController.execute", ex);
            response.setCodeAndMsg(Constant.ERROR_CODE_9999, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
    	model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        return RET_JSP;
    }
}
