package cn.emagsoftware.xfb.pojo;

import java.util.Date;

/**
 * 征信人信息     
* 创建人：shenzhiqiang    
* 创建时间：2015-11-27 上午9:58:12       
* @version      
*
 */
public class CreditInfo {

	 private Long id;
	 
	 private String userId;  //查询人id

	 private String userName;  //姓名
	 
	 private String cardNumber;  //身份证号码
	 
	 private String trxNo;  //交易代码
	 
	 private Date queryTime;  //查询时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTrxNo() {
		return trxNo;
	}

	public void setTrxNo(String trxNo) {
		this.trxNo = trxNo;
	}

	public Date getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	 


}
