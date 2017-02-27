/**
 * @(#)WcfCallUtils.java	1.0	2015-8-4
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.emagsoftware.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wcf.client.IInternal;
import wcf.client.Internal;
import cn.tyiti.xfb.dao.InterfaceLogDao;
import cn.tyiti.xfb.utils.LogUtil;

/**
 * java 调用 .net Wcf.
 * 
 * @version 1.0 2015-8-4
 * @author Black
 */

@Repository("wcfCallUtils")
public class WcfCallUtils {
	private static IInternal iInternal = null;
	private static String GLOBAL_KEY = null;
	@Autowired
	InterfaceLogDao interfaceLogDao;
	/**
	 * 
	 * 单列Internal实例.
	 * @author Black
	 * @date 2015-8-4 下午5:52:56
	 *
	 * @return
	 */
	public static IInternal getInstance() throws Exception{
		if(iInternal == null){
			iInternal = new Internal().getBasicHttpBindingIInternal();
			GLOBAL_KEY = CommonUtils.getPropertiesValue("config","wcf.key");
		}
		return iInternal;
	}

	/**
	 * 
	 * 调用注册接口.
	 * @author Black
	 * @date 2015-8-4 下午5:56:14
	 *
	 * @param userId 用户Id 接口调用失败时使用
	 * @param loginName 登录名
	 * @param password  密码
	 * @param userName  真实姓名
	 * @param cardNumber 身份证号码
	 * @return
	 */
	public Boolean callRegister(Integer userId, String loginName, String password, String userName, String cardNumber) {
		String preStr = "执行方法WcfCallUtils.callRegister";
		try {
	        // 开始时间
	        long   begin_time = System.currentTimeMillis();
			boolean result = getInstance().register(GLOBAL_KEY, loginName, password, userName, cardNumber);
			if(!result){//返回失败记录日志
				LogUtil.error(preStr+"返回失败result："+result);
				LogUtil.info(preStr+"返回失败result："+result);
			}
	        // 结束时间
	        long end_time = System.currentTimeMillis();
            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
		} catch (Exception ex){
			LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
			interfaceLogDao.insertInterfacelog(userId, "Register");
		}
		return true;
	}
	
	/**
	 * 
	 * 调用修改密码接口.
	 * @author Black
	 * @date 2015-8-4 下午5:56:02
	 *
	 * @param userId 用户Id 接口调用失败时使用
	 * @param loginName 登录名
	 * @param password  密码
	 * @return
	 */
	public Boolean callSetPassword(Integer userId, String loginName, String password) {
		String preStr = "执行方法WcfCallUtils.callSetPassword";
		try {
	        // 开始时间
	        long   begin_time = System.currentTimeMillis();
			boolean result = getInstance().setPassword(GLOBAL_KEY, loginName, password);
			if(!result){//返回失败记录日志
				LogUtil.error(preStr+"返回失败result："+result);
			}
	        // 结束时间
	        long end_time = System.currentTimeMillis();
            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
		} catch (Exception ex){
			LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
			interfaceLogDao.insertInterfacelog(userId, "SetPassword");
		}
		return true;
	}
	
	/**
	 * 
	 * 调用更改用户状态接口
	 * @author Black
	 * @date 2015-8-5 下午2:13:50
	 *
	 * @param userId 用户Id 接口调用失败时使用
	 * @param loginName 用户名
	 * @param state 0:启用1:停用
	 * @return
	 */
	public Boolean callSetUserStatus(Integer userId, String loginName, String state) {
		Boolean bool;
		String preStr = "执行方法WcfCallUtils.callSetUserStatus";
		if("0".equalsIgnoreCase(state)){
			bool = true;
		} else if("1".equalsIgnoreCase(state)){
			bool = false;
		} else {
			bool = false;
		}
		try {
	        // 开始时间
	        long   begin_time = System.currentTimeMillis();
			boolean result = getInstance().setUserStatus(GLOBAL_KEY, loginName, bool);
			if(!result){//返回失败记录日志
				LogUtil.error(preStr+"返回失败result："+result);
			}
	        // 结束时间
	        long end_time = System.currentTimeMillis();
            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
		} catch (Exception ex){
			LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
			interfaceLogDao.insertInterfacelog(userId, "SetUserStatus");
		}
		return true;
	}
	
	/**
	 * 
	 * 更新用户类型 
	 * @author Black
	 * @date 2015-8-24 下午4:09:18
	 *
	 * @param userId 用户Id 接口调用失败时使用
	 * @param loginName 用户名
	 * @param userType 用户类型 -1学生 -5普通用户 上班族
	 * @return
	 * @throws Exception
	 */
	public Boolean callSetMemberType(Integer userId, String loginName, Integer userType) {
		String preStr = "执行方法WcfCallUtils.callSetMemberType";
		try {
	        // 开始时间
	        long   begin_time = System.currentTimeMillis();
			boolean result = getInstance().setMemberType(GLOBAL_KEY, loginName, userType);
			if(!result){//返回失败记录日志
				LogUtil.error(preStr+"返回失败result："+result);
			}
	        // 结束时间
	        long end_time = System.currentTimeMillis();
            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
		} catch (Exception ex){
			LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
			interfaceLogDao.insertInterfacelog(userId, "SetMemberType");
		}
		return true;
	}
	
	/**
	 * 
	 * 添加银行卡信息
	 * @author Black
	 * @date 2015-9-8 下午4:58:41
	 *
	 * @param bankCardId 银行卡Id 接口调用失败时使用
	 * @param loginName 用户名
	 * @param name 真实姓名
	 * @param type 银行卡类型   中文：信用卡 储蓄卡
	 * @param bank 开户行
	 * @param number 银行卡号
	 * @return
	 * @throws Exception
	 */
	public Boolean callAddBankCard(Integer bankCardId,String loginName,String name,
			String type,String bank,String number) {
		String preStr = "执行方法WcfCallUtils.callAddBankCard";
		//A1:银行卡 A2:信用卡
		if("A1".equals(type)){
			type = "储蓄卡";
		} else if("A2".equals(type)){
			type = "信用卡";
		} else {
			LogUtil.error(preStr+"银行卡类型不能为空");
		}
		try {
	        // 开始时间
	        long   begin_time = System.currentTimeMillis();
			boolean result = getInstance().addBankCard(GLOBAL_KEY, loginName, name, type, bank, number);
			if(!result){//返回失败记录日志
				LogUtil.error(preStr+"返回失败result："+result);
			}
	        // 结束时间
	        long end_time = System.currentTimeMillis();
            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
		} catch (Exception ex){
			LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
			interfaceLogDao.insertInterfacelog(bankCardId, "AddBankCard");
		}
		return true;
	}
	
	/**
	 * 
	 * 设置分期商城订单状态
	 * @author Black
	 * @date 2015-9-11 下午5:38:17
	 *
	 * @param orderId 订单Id 接口调用失败时使用
	 * @param orderName 订单号
	 * @param orderStatus 订单状态
	 * @return
	 */
	public Boolean callSetOrderStatus(Integer orderId, String orderName, Integer orderStatus) {
		/*String preStr = "执行方法WcfCallUtils.callSetOrderStatus";
		int length = orderName.trim().length();
		LogUtil.info(preStr+"orderName length:"+length);
		//长度为36位 表示为uuid 商城下的单
		if(length == 36) {
			try {
		        // 开始时间
		        long   begin_time = System.currentTimeMillis();
				boolean result = getInstance().setOrderStatus(GLOBAL_KEY, orderName, orderStatus);
				if(!result){//返回失败记录日志
					LogUtil.error(preStr+"返回失败result："+result);
				}
		        // 结束时间
		        long end_time = System.currentTimeMillis();
	            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
			} catch (Exception ex){
				LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
				interfaceLogDao.insertInterfacelog(orderId, "SetOrderStatus");
			}
		}*/
		return true;
	}

	/**
	 * 
	 * 设置分期商城账单状态
	 * @author Black
	 * @date 2015-9-11 下午5:55:11
	 *
	 * @param billId 账单Id 接口调用失败时使用
	 * @param billNo 账单号
	 * @param pay 支付方式 1：支付宝 2：财付通
	 * @param payCode 支付流水号
	 * @param billStatus 账单状态
	 * @return
	 */
	public Boolean callSetBillStatus(Integer billId, String billNo, Integer pay, String payCode, Integer billStatus) {
		String preStr = "执行方法WcfCallUtils.callSetBillStatus";
		int length = billNo.trim().length();
		LogUtil.info(preStr+"billName length:"+length);
		//长度为36位 表示为uuid 商城下的单
		if(length > 12) {
			try {
		        // 开始时间
		        long   begin_time = System.currentTimeMillis();
				boolean result = getInstance().setBillStatus(GLOBAL_KEY, billId, billNo, pay, payCode, billStatus);
				if(!result){//返回失败记录日志
					LogUtil.error(preStr+"返回失败result："+result);
				}
		        // 结束时间
		        long end_time = System.currentTimeMillis();
	            LogUtil.info(preStr+"耗时"+ (end_time - begin_time) + "ms");
			} catch (Exception ex){
				LogUtil.error(preStr+"抛出异常，异常信息："+ex.getMessage());
				interfaceLogDao.insertInterfacelog(billId, "SetBillStatus");
			}
		}
		return true;
	}
}
