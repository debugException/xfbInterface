package cn.tyiti.xfb.bojo;

import java.util.Date;

/**
 * 
 * 类名称：PayRecode <br/>
 * 类描述：支付流水VO  <br/>
 * @date 2015年10月27日11:21:40 <br/>
 * @author SHENWU
 * @version V1.0
 */
public class PayRecode extends PublicProperties {

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 账单ID
	 */
	private Integer billId;
	
	/**
	 * 支付流水号
	 */
	private String tradeNo;
	
	/**
	 * 支付类型	a1.支付宝	a2.微信
	 */
	private String payType;
	
	/**
	 * 支付金额
	 */
	private String payAmount;
	
	/**
	 * 支付状态	A1.未支付（默认）	A2.已支付
	 */
	private String payState;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
}
