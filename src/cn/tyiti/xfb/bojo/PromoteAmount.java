/**
 * @(#)PromoteAmount.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.bojo;

import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * 提升额度-获取数据Entity.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public class PromoteAmount extends BaseRspBean{
	/*会员类型
	A1:工作（上班族）
	A2:学生*/
	private String type;
	
	//用户推荐数量
	private String userRecomNum;
	
	/*个人信息*/
	private PromoteCommon memberInfo;
	
	/*工作信息*/
	private PromoteCommon jobInfo;
	
	/*学生信息*/
	private PromoteCommon studentInfo;
	
	/*联系人信息*/
	private PromoteCommon contactsInfo;
	
	/*储蓄卡*/
	private PromoteCommon depositCard;
	
	/*信用卡*/
	private PromoteCommon creditCard;
	
	/*驾照*/
	private PromoteCommon drivingLicense;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserRecomNum() {
		return userRecomNum;
	}

	public void setUserRecomNum(String userRecomNum) {
		this.userRecomNum = userRecomNum;
	}

	public PromoteCommon getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(PromoteCommon memberInfo) {
		this.memberInfo = memberInfo;
	}

	public PromoteCommon getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(PromoteCommon jobInfo) {
		this.jobInfo = jobInfo;
	}

	public PromoteCommon getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(PromoteCommon studentInfo) {
		this.studentInfo = studentInfo;
	}

	public PromoteCommon getContactsInfo() {
		return contactsInfo;
	}

	public void setContactsInfo(PromoteCommon contactsInfo) {
		this.contactsInfo = contactsInfo;
	}

	public PromoteCommon getDepositCard() {
		return depositCard;
	}

	public void setDepositCard(PromoteCommon depositCard) {
		this.depositCard = depositCard;
	}

	public PromoteCommon getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(PromoteCommon creditCard) {
		this.creditCard = creditCard;
	}

	public PromoteCommon getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(PromoteCommon drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	
}
