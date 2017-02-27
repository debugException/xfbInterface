package cn.emagsoftware.frame.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.Logger;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.XStreamUtils;

/**
 * @Title: 控制层基类
 */
@Component
public class BaseController {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    protected HttpServletRequest request;
    @Logger
    protected static ILog log;

    /**
     * 返回空白JSP画面
     */
    protected static final String RET_JSP = "ret";

    /**
     * 重定向画面（URL转到目标）
     */
    protected static final String REDIRECT_JSP = "redirect";

    /**
     * 返回上下文
     *
     * @return
     */
    protected ApplicationContext getCtx() {
        return ctx;
    }

    /**
     * 
     * 获取请求头中用户ID
     * @author Black
     * @date 2015-8-12 下午3:40:26
     *
     * @return
     */
    protected Integer getUserId(){
    	Integer result = 0;
    	String userId = request.getHeader("userId");
    	//ios 会传  (null) 字符串 对这个进行过滤
    	if(!StringUtils.isEmpty(userId) && !"(null)".equals(userId)){
    		result = Integer.valueOf(userId);
    	} 
    	return result;
    }
    /**
     * 返回方法
     *
     * @param logId
     * @param model
     * @param responseBean
     * @param stime
     * @return
     */
    protected String finishBack(long logId, Map<String, String> model, BaseRspBean responseBean, long stime) {
        String xml = XStreamUtils.parseObjToXml(responseBean);
        long etime = System.currentTimeMillis();
        log.info(logId, "业务处理结束！花费" + (etime - stime) + "毫秒！");
        log.info(logId, "returnXML:" + xml);
        model.put(Constant.RETURN_MESSAGE, xml);
        return RET_JSP;
    }



    public String getIpAddr() {

        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("Proxy-Client-IP");

        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getHeader("WL-Proxy-Client-IP");

        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

            ip = request.getRemoteAddr();

        }

        return ip;

    }
    
    /**
     * 根据request获取请求的用户参数
     * @return 
     * @return
     */
    protected <T> T getParamConvertEntity(Class cls) {
    	Object obj = null;
        try {
        	obj = cls.newInstance();
        	Map paramMap = new HashMap();
        	paramMap.putAll(request.getParameterMap());
        	//设置用户ID
        	paramMap.put("userId", getUserId());
            BeanUtils.populate(obj, paramMap);
            log.debug("请求的用户信息为："+JSONObject.fromObject(obj).toString());
        } catch (Exception ex) {
            log.error("用户请求信息转换错误" + ex.getMessage(), ex);
        }
        return (T) obj;
    }
}