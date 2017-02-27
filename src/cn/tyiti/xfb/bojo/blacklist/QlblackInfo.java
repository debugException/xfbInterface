package cn.tyiti.xfb.bojo.blacklist;   
/** 
 * 创建时间：2015-11-24 下午9:26:59  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：QlblackInfo.java  
 * 类说明：  
 */
public class QlblackInfo {
	/* VARCHAR(20)  not null COMMENT '姓名',
	 VARCHAR(18) not null  COMMENT'身份号',	
	 VARCHAR(11) not null  COMMENT '黑名单信息表id',	
	 VARCHAR(4)  DEFAULT NULL COMMENT '返回结果（0/1）',
	 VARCHAR(30) DEFAULT NULL   COMMENT '信息',
	 VARCHAR(20) DEFAULT NULL  COMMENT'查询时间',
	*/
	
	private String id ;
	private String name;
	private String idcode;
	private String msgId;
	private String code;
	private String message;
	private String querytime;
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
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQuerytime() {
		return querytime;
	}
	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
}
 