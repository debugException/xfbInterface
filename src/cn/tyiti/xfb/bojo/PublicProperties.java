/**
 * @(#)PublicProperties.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.bojo;

import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * 公共属性Entity.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public class PublicProperties extends BaseRspBean {
	//Id
	private Integer id;
	//用户ID
	private Integer userId;
	/*//是否给分
	private String isTopoints;
	//审核状态
	private String verifyState;
	//核实人ID
	private Integer verifyUserid;
	//核实时间
	private String verifyTime;
	//提交时间
	private String submitTime;
	//维护时间
	private String modifyTime;
	//备注
	private String remarks;*/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/*public String getIsTopoints() {
		return isTopoints;
	}
	public void setIsTopoints(String isTopoints) {
		this.isTopoints = isTopoints;
	}
	public String getVerifyState() {
		return verifyState;
	}
	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	public Integer getVerifyUserid() {
		return verifyUserid;
	}
	public void setVerifyUserid(Integer verifyUserid) {
		this.verifyUserid = verifyUserid;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}*/
	
}
