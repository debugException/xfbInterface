package cn.emagsoftware.xfb.controller;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.frame.exception.BaseException;
import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.Logger;
import cn.emagsoftware.utils.*;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.CommonConstant;
import cn.emagsoftware.xfb.dto.AppHeartbeatRsp;
import cn.emagsoftware.xfb.dto.AppRsp;
import cn.emagsoftware.xfb.dto.SysConfigRsp;
import cn.emagsoftware.xfb.pojo.App;
import cn.emagsoftware.xfb.pojo.SysConfig;
import cn.emagsoftware.xfb.service.AppSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 系统相关接口
 */
@Controller
@RequestMapping(value = "/appSys")
public class AppSysController extends BaseController {

    @Autowired
    private AppSysService appSysService;
    @Logger
    protected static ILog log;

    /**
     * 返回所有CLIENT_CONFIG字典类型的配置信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getSysConfig", method = {RequestMethod.GET, RequestMethod.POST})
    public String getSysConfig(Map<String, String> model) {
        log.debug("AppSysController.getSysConfig");
        SysConfigRsp response = new SysConfigRsp();
        try {
            List<SysConfig> adList = appSysService.getSysConfig();
            if (CommonUtils.isEmpty(adList)) {
                response.setSysConfigListSize(0);
            } else {
                response.setSysConfigList(adList);
                response.setSysConfigListSize(adList.size());
            }
            response.setResultCode(Constant.SUCCESS_CODE);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.SUCCESS_CODE));

        } catch (BaseException e) {
            response.setResultCode(e.getErrorCode());
            response.setResultMessage(e.getErrorMsg());
        } catch (Exception e) {
            log.error("AppSysController.getSysConfig", e);
            response.setResultCode(Constant.ERROR_CODE_9999);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
        try {
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("AppSysController.getSysConfig", e);
        }
        return RET_JSP;
    }

    /**
     * 心跳接口
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/appHeartbeat", method = {RequestMethod.GET, RequestMethod.POST})
    public String appHeartbeat(Map<String, String> model) {
        log.debug("AppSysController.appHeartbeat,parameter[{uuid,imie,customerID},{" + request.getParameter("uuid") + "," + request.getHeader(CommonConstant.REQUEST_PARAM_IMIE) + ","
                + UserHolder.getUserCustomerID() + "}]");
        AppHeartbeatRsp response = new AppHeartbeatRsp();
        try {
            // 1.取参数
            String uuid = request.getParameter("uuid");
            long customerID = UserHolder.getUserCustomerID();
            if (CommonUtils.isEmpty(uuid)) {
                // uuid为空，则认为是第一次调用
                uuid = CommonUtils.getUUID();

            }
            String imie = request.getHeader(CommonConstant.REQUEST_PARAM_IMIE);
            appSysService.appHeartbeat(uuid, CommonUtils.isEmpty(imie) ? "" : imie, customerID);
            response.setAppUUID(uuid);
            response.setResultCode(Constant.SUCCESS_CODE);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.SUCCESS_CODE));

        } catch (BaseException e) {
            response.setResultCode(e.getErrorCode());
            response.setResultMessage(e.getErrorMsg());
        } catch (Exception e) {
            log.error("AppSysController.appHeartbeat", e);
            response.setResultCode(Constant.ERROR_CODE_9999);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
        try {
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("AppSysController.appHeartbeat", e);
        }
        return RET_JSP;
    }

    /**
     * 根据type返回本应用的最新详细信息type 1：android; 2：ios
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getNewestApp", method = {RequestMethod.GET, RequestMethod.POST})
    public String getNewestApp(Map<String, String> model) {
        log.debug("AppSysController.getNewestApp,parameter[{type},{" + request.getHeader(CommonConstant.REQUEST_PARAM_PLATFORM) + "}]");
        AppRsp response = new AppRsp();
        try {
            String channelCode = request.getHeader(CommonConstant.REQUEST_PARAM_CHANNEL_CODE);
            String typeStr = request.getHeader(CommonConstant.REQUEST_PARAM_PLATFORM);
            if (CommonUtils.isEmpty(typeStr) || !ValidateUtils.checkType(typeStr)) {
                response.setResultCode(CommonConstant.ERROR_CODE_1003);
                response.setResultMessage(CommonConstant.ERROR_MESSAGE.get(CommonConstant.ERROR_CODE_1003));
                model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
                return RET_JSP;
            }
            int type = Integer.valueOf(typeStr);
            App app = appSysService.getNewestApp(type, channelCode);
            if (null == app) {
                response.setResultCode(Constant.ERROR_CODE_9999);
                response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
            } else {
                app.setAppUrl(ConfigCache.getResourceTomcatPath() + app.getAppUrl());
                response.setApp(app);
            }
            response.setResultCode(Constant.SUCCESS_CODE);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.SUCCESS_CODE));

        } catch (BaseException e) {
            response.setResultCode(e.getErrorCode());
            response.setResultMessage(e.getErrorMsg());
        } catch (Exception e) {
            log.error("AppSysController.getNewestApp", e);
            response.setResultCode(Constant.ERROR_CODE_9999);
            response.setResultMessage(Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
        try {
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("AppSysController.getNewestApp", e);
        }
        return RET_JSP;
    }
}
