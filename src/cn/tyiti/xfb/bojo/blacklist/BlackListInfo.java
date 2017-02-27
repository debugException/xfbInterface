package cn.tyiti.xfb.bojo.blacklist;   
/** 
 * 创建时间：2015-11-24 下午9:29:15  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：BlackListInfo.java  
 * 类说明：  
 */
public class BlackListInfo {
	/* VARCHAR(20)  not null COMMENT '姓名',
	 VARCHAR(18) not null  COMMENT'身份号',	
	 VARCHAR(11) not null  COMMENT '黑名单信息表id',
	 varchar(2) COMMENT '  99:权限不足  1:被执行人   3:逾期 31-60 天   5:逾期 61-90 天   6:逾期 91-180 天  7:违约>180 天',
	 VARCHAR(2)  not null COMMENT '来源代码(1 金融机构)',
	 VARCHAR(20)  not null COMMENT '业务发生时间',
	 VARCHAR(20)  not null COMMENT '金额范围（ 无、0-1000、1000-5000、5000-20000、2W-10W、10W 以上 )',
	 VARCHAR(2)  not null COMMENT '数据状态（1:已验证  2:未验证 ）',
	 VARCHAR(20) DEFAULT NULL  COMMENT'查询时间',
	 */
	private String id ;
	private String name;
	private String idcode;
	private String msgId;
	private String idtype;
	private String grade;
	private String sourceid;
	private String databuildtime;
	private String moneybound;
	private String datastatus;
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

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getDatabuildtime() {
		return databuildtime;
	}

	public void setDatabuildtime(String databuildtime) {
		this.databuildtime = databuildtime;
	}

	public String getMoneybound() {
		return moneybound;
	}

	public void setMoneybound(String moneybound) {
		this.moneybound = moneybound;
	}

	public String getDatastatus() {
		return datastatus;
	}

	public void setDatastatus(String datastatus) {
		this.datastatus = datastatus;
	}

	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
	
}
 