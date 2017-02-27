package cn.emagsoftware.frame.interceptor;

import cn.emagsoftware.frame.bean.ImeiToken;
import cn.emagsoftware.frame.exception.ServiceException;
import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.Logger;
import cn.emagsoftware.utils.AppUtils;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.UserHolder;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.AppConstants;
import cn.emagsoftware.xfb.constants.SysUserConstant;
import cn.emagsoftware.xfb.dto.CustomerRsp;
import cn.emagsoftware.xfb.pojo.Customer;
import cn.emagsoftware.xfb.pojo.TransLog;
import cn.emagsoftware.xfb.service.AppSysService;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class CheckSessionInterceptor extends HandlerInterceptorAdapter {

    @Logger
    private static ILog log;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private AppSysService appSysService;

    public static final List<String> STATIC_RESOURCES = new ArrayList<String>();

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    static {
        STATIC_RESOURCES.add(".js");
        STATIC_RESOURCES.add(".html");
        STATIC_RESOURCES.add(".htm");
        STATIC_RESOURCES.add(".css");
        STATIC_RESOURCES.add(".png");
        STATIC_RESOURCES.add(".jpg");
        STATIC_RESOURCES.add(".gif");
    }

    private List<String> ignoreUri = Collections.emptyList();

    private List<String> backstageUri = Collections.emptyList();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);    //To change body of overridden methods use File | Settings | File Templates.
    }
    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle start...");
        // 获取URI地址
        String requestUri = getRequestUri(request);

        CustomerRsp customerRsp = new CustomerRsp();



//        UserHolder.clear();
        request.setCharacterEncoding("UTF-8");
        if (isIgnoreRequest(request)) {
            return true;
        }

//      用户头信息校验
        String authentication = request.getHeader("authentication");
//		log.info("preHandle info:| authentication=" + authentication);
//		if (StringUtils.isBlank(authentication)) {
//			throw new ServiceException("check session failure", AppConstants.ERROR_REQUEST_FIELD_INVALID);
//		}
        Map<String, String> authMap = AppUtils.splitPairValues(authentication);
        String userToken = authMap.get("userToken");
        if (StringUtils.isNotBlank(userToken)) {
            Customer customer = getUser(userToken, request,customerRsp);
            if(customer!=null){
                log.info("Customer info:|user.id=" + customer.getCustomerId() + "|user.openID=" + customer.getOpenId() + "|user.openType=" + customer.getOpenType() + "|user.openNick=" + customer.getOpenNick());
            } else{
//
//                customer = new Customer();
//                customer.setOpenId("");
//                customer.setOpenNick("");
//                customer.setOpenType(1);
//                customer.setCustomerId(1L);

                request.setAttribute(Constant.RETURN_MESSAGE,
                        JsonUtils.getJSONString(customerRsp));
                request.getRequestDispatcher("/WEB-INF/jsp/ret.jsp").forward(request, response);
                log.debug("CheckSignValueInterceptor.customerRsp="+JsonUtils.getJSONString(customerRsp));
                return false;
            }


//            UserHolder.setUser(customer);
        }

        String imei = request.getHeader("imei");
        String channelCode = request.getHeader("channelCode");
        String platform = request.getHeader("platform");
        try {
            TransLog transLog = new TransLog();
            transLog.setChannelCode(channelCode);
            transLog.setPlatform(platform);
            transLog.setImei(imei);
            transLog.setCustomerId(UserHolder.getUserCustomerID());
            transLog.setRequestUri(requestUri);
            appSysService.insertTransLog(transLog);
        } catch (Exception e) {
            log.info("insertTransLog error:customerId:" + UserHolder.getUserCustomerID() + ";imei:" + imei + ";channelCode:" + channelCode + ";platform:" + platform + ";requestUri:" + requestUri);
        }
        return true;
    }

    private Customer getUser(String userToken, HttpServletRequest request,CustomerRsp customerRsp) throws TimeoutException, InterruptedException, MemcachedException, ServiceException {

            Customer user = memcachedClient.get(userToken);
            if (user != null) {
                // 正常会话状态的用户则维持会话心跳
//            String userKey = "userKey_" + user.getCustomerId() + "_" + user.getOpenId();
                String userKey = "userKey_" + user.getUserId();
                String serviceToken = memcachedClient.get(userKey);
                if(userToken.equals(serviceToken)){
                    int sessionTimeout = AppConstants.DEFAULT_SESSION_TIMEOUT_SECONDS;
                    memcachedClient.set(userKey, sessionTimeout, userToken);
                    memcachedClient.set(userToken, sessionTimeout, user);
                }else{
                    customerRsp.setResultCode("2002");
                    customerRsp.setResultMessage("用户已经在其它终端登录");
                    return null;
                }

            }else{
                customerRsp.setResultCode("2001");
                customerRsp.setResultMessage("用户登录超时或者未登录");
            }
            return user;



//        else {
//			if (!isBackstageUri(request)) {
//				log.error("checkSession.checkRequestUri|userToken={}|requestUri={}|ipAddress={}|errorMsg={}");
//				throw new ServiceException("check session failure", AppConstants.ERROR_SESSION_TIMEOUT);
//			}
//
//			String imei = request.getParameter("imei");
////			String imei = "12345678EEE222";
//			if (StringUtils.isBlank(imei)) {
//				log.error("checkSession.checkRequestImei|userToken=" + userToken + "|imei=" + imei);
//				throw new ServiceException("check session failure", AppConstants.ERROR_SESSION_TIMEOUT);
//			}
//			ImeiToken imeiToken = new ImeiToken();
//			imeiToken.setImei(imei);
//			imeiToken.setUserToken(userToken);
//            user = appSysService.getUserByImeiAndToken(imeiToken);
////			user = userService.queryUser(imeiToken);
//			// TODO:
//			if (null == user) {
//				log.error("checkSession.getUser|userToken={}|imei={}|errorMsg={}");
////				throw new ServiceException("check session failure", AppConstants.ERROR_SESSION_TIMEOUT);
//			}else{
//
//            }
//		}

    }

    /**
     * 不需要校验的请求路径
     * @param request
     * @return
     */
    private boolean isIgnoreRequest(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        if (StringUtils.isNotBlank(uri)) {
            uri = uri.substring(request.getContextPath().length() + 1);
        }
        for (String it : STATIC_RESOURCES) {
            if (uri.endsWith(it)) {
                return true;
            }
        }

        if(uri.endsWith("login.do")||uri.endsWith("handleVerifCode.do")
        		||uri.endsWith("registration.do")||uri.endsWith("getVersion.do")){
            return true;
        }
        System.out.println(uri);
        for (String it : ignoreUri) {
            if (pathMatcher.match(it, uri)) {
                return true;
            }
        }


        return false;
    }

    private boolean isBackstageUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (String url : backstageUri) {
            if (uri.endsWith(url))
                return true;
        }
//        todo   zhoujian 150402  false-->true
        return true;
    }

    private String getRequestUri(HttpServletRequest request) {
        if (request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE) != null) {
            return (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
        } else {
            return request.getRequestURI();
        }
    }

}
