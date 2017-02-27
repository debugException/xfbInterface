package cn.emagsoftware.xfb.pojo;

import java.util.Date;

/**
 * 返回给91征信
*      
* 项目名称：xfbInterface     
* 类名称：LoanInfoBack     
* 类描述：     
* 创建人：shenzhiqiang    
* 创建时间：2015-12-2 上午10:57:25       
* @version      
*
 */
public class LoanInfoBack {
    private Short borrowType;//借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押
	private Short borrowState ;//借款状态 0.未知1.拒贷2.批贷已放款3.批贷未放款4.借款人放弃申请5.审核中6.待放款
    private Short borrowAmount;//借款金额
    private Date contractDate;//合同日期
    private Short loanPeriod;//批贷期数
    private Short repayState;//还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+
    private Long arrearsAmount; //欠款金额
	private String companyCode; //公司代码
	
	public String getCompanyCode() {
		return companyCode;
	}

	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
    
	public Short getBorrowType() {
		return borrowType;
	}
	
	public void setBorrowType(Short borrowType) {
		this.borrowType = borrowType;
	}
	
	public Short getBorrowState() {
		return borrowState;
	}
	
	public void setBorrowState(Short borrowState) {
		this.borrowState = borrowState;
	}
	
	public Short getBorrowAmount() {
		return borrowAmount;
	}
	
	public void setBorrowAmount(Short borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	
	public Date getContractDate() {
		return contractDate;
	}
	
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	
	public Short getLoanPeriod() {
		return loanPeriod;
	}
	
	public void setLoanPeriod(Short loanPeriod) {
		this.loanPeriod = loanPeriod;
	}
	
	public Short getRepayState() {
		return repayState;
	}
	
	public void setRepayState(Short repayState) {
		this.repayState = repayState;
	}
	
	public Long getArrearsAmount() {
		return arrearsAmount;
	}
	
	public void setArrearsAmount(Long arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}

}

