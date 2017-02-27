package cn.tyiti.xfb.bojo;

import java.util.Date;

/**
 * 
 * 得分记录表实体类
 * @version 1.0 2015-10-10
 * @author KELLEN
 * */
public class ScoreLog {
	
	/**ID*/   	public Integer id;
	/**用户ID*/ 	public Integer userId;
	/**用户得分*/	public Integer score;
	/**核实人ID*/	public String verifyUserId;
	/**核实时间*/	public Date verifyTime;
	/**状态:A1-有效A2-失效*/public String flag;
	/**备注*/ 	public String remarks;
	
	public ScoreLog(Integer userId,Integer score){
		this.userId = userId;
		this.score = score;
	}
	
	public ScoreLog(Integer userId,Integer score,String verifyUserId){
		this.userId = userId;
		this.score = score;
		this.verifyUserId = verifyUserId;
	}
	
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getVerifyUserId() {
		return verifyUserId;
	}
	public void setVerifyUserId(String verifyUserId) {
		this.verifyUserId = verifyUserId;
	}
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
