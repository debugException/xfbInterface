/**
 * @(#)PromoteCommon.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.bojo;

/**
 * 提升额度获取数据公用的属性.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public class PromoteCommon {
	//id
	private Integer id;
	/*A1:草稿
	A2:已退回
	A3:审核中
	A4:审核通过 */
	private String state;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
