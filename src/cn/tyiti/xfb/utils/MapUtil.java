/**
 * @(#)MapConvert.java	1.0	2015-8-18
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Map工具类.
 * 
 * @version 1.0 2015-8-18
 * @author Black
 */
public class MapUtil {
	/**
     * 根据request获取请求的用户参数
     * @return 
     * @return
     */
    public static <T> T getParamConvertEntity(Class cls, HttpServletRequest request) {
    	Object obj = null;
        try {
        	obj = cls.newInstance();
        	Map paramMap = new HashMap();
        	paramMap.putAll(request.getParameterMap());
        	//设置用户ID
        	paramMap.put("userId", Integer.valueOf(request.getHeader("userId")));
            BeanUtils.populate(obj, paramMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (T) obj;
    }
}
