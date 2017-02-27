package cn.tyiti.xfb.bojo.blacklist;

import java.util.Date;


/**
 * 创建时间：2015-11-24 下午9:09:53 项目名称：xfbManage
 * 
 * @author liminghua
 * @version 1.0
 * @since JDK 1.6 文件名称：bListInfo.java 类说明：
 * */
public class BlistInfo {

	private String id ;
	private String name; // '姓名',
	private String idcode; // '身份号',
	private String status; // '返回状态状态(0-成功，否则失败错误代码)',
	private String value; // '查询信息',
	private String resultcode; // '查询量(0-正常,1-预警)',
	private String resultdesc; // '查询量信息',
	private String queryInterface ; // '查询接口',
	private String userId;
	private String loginName ;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultdesc() {
		return resultdesc;
	}

	public void setResultdesc(String resultdesc) {
		this.resultdesc = resultdesc;
	}

	public String getQueryInterface() {
		return queryInterface;
	}

	public void setQueryInterface(String queryInterface) {
		this.queryInterface = queryInterface;
	}

}
