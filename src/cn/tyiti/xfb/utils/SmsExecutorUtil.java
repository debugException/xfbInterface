/**
 * @(#)SmsExecutorUtil.java	1.0	2015-8-6
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.emagsoftware.utils.CommonUtils;

/**
 * 发送短信多线程工具类
 * 
 * @version 1.0 2015-8-6
 * @author Black
 */
public class SmsExecutorUtil {

    private static final Logger log = Logger.getLogger(SmsExecutorUtil.class);
	private static ExecutorService exec;
	//发送短信异步调用线程池大小 配置为0,默认获取服务器cpu个数
	public static Integer smsAsynSize = Runtime.getRuntime().availableProcessors();
	
	/**
	 * 
	 * 线程安全的懒汉单列
	 * @author Black
	 * @date 2015年5月11日 下午3:05:01
	 *
	 * @return
	 */
	public static synchronized ExecutorService getInstanceExecutors() { 
		if (exec == null) {// 创建一个固定大小的线程池
			log.info("-------- 创建一个固定大小的线程池------------smsAsynSize:"+smsAsynSize);
			exec = Executors.newFixedThreadPool(smsAsynSize);
		}  
		return exec;
	}  
	
	/**
	 * 
	 * 短信提醒
	 * 用于提醒到期日前小于3天提醒
	 * @author Black
	 * @date 2015-8-6 下午2:06:43
	 *
	 * @param postData 手机号码
	 * @param userPhone 消息
	 */
	public static void sendSMS(String postData, String userPhone){
		log.info("用户：" + userPhone + "--------------线程执行开始------------");
		getInstanceExecutors().execute(createTask(postData, userPhone));
	}
	
	/**
	 * 
	 * 通过多线程来处理异步发送短信
	 * @author Black
	 * @date 2015-8-6 下午1:56:22
	 *
	 * @param postData 手机号码
	 * @param userPhone 消息
	 * @return
	 * @throws Exception
	 */
	private static Runnable createTask(final String postData, final String userPhone) {
    	return new Runnable() {
            public void run(){
            	try{
//            		短信发送平台选择 
            		String smsSendFlag = CommonUtils.getPropertiesValue("config","sms_send_flag");
            		LogUtil.info("--------------smsSendFlag--------------"+smsSendFlag+"||||");
            		if("1".equals(smsSendFlag)){//软维短信平台
            			SmsNewUtil.sendSms(postData,userPhone);
            		} else {
            			SmsUtil.SMS(postData,userPhone);
            		}
            		log.info("用户：" + userPhone +"--------------线程执行结束------------");
                } catch (Exception ex){
                	LogUtil.error(ex.getMessage());
                }
            };
            
    	};
    }
}
