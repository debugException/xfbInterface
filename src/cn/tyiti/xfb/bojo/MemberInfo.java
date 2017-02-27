/**
 * @(#)MemberInfo.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.bojo;


/**
 * 提升额度-获取个人信息/提升额度-提交个人信息Entity.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public class MemberInfo extends PublicProperties {
	//姓名
	private String name;
	//身份证号码
	private String cardNumber;
	//常住省
	private String province;
	//常住市
	private String city;
	//常住县
	private String county;
	//常住地址
	private String address;
	//审核状态
	private String verifyState;
	//交易密码
	private String tradePassword;
	//交易密码错误次数
	private String tpErrorAmount;
	//交易密码锁定时间
	private String tpLockTime;
	//地理位置
	private String geolocation;
	
	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getTpErrorAmount() {
		return tpErrorAmount;
	}

	public void setTpErrorAmount(String tpErrorAmount) {
		this.tpErrorAmount = tpErrorAmount;
	}

	public String getTpLockTime() {
		return tpLockTime;
	}

	public void setTpLockTime(String tpLockTime) {
		this.tpLockTime = tpLockTime;
	}

	public String getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(String geolocation) {
		this.geolocation = geolocation;
	}
	
}
