package cn.tyiti.xfb.bojo;

import java.util.Date;

public class BankCardInfo {
	private Integer id;
	private Integer userId;
	private String name;
	private String cardNo;
	private String openingBank;
	private String ylNumber;
	private String type;
	private String verifyState;
	private Integer verifyUserid;
	private Date verifyTime;
	private Date submitTime;
	private Date modifyTime;
	private String remarks;
	private String isToPoints;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getOpeningBank() {
		return openingBank;
	}
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}
	public String getYlNumber() {
		return ylNumber;
	}
	public void setYlNumber(String ylNumber) {
		this.ylNumber = ylNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Date getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsToPoints() {
		return isToPoints;
	}
	public void setIsToPoints(String isToPoints) {
		this.isToPoints = isToPoints;
	}
	
}
