package cn.emagsoftware.xfb.pojo;

import cn.emagsoftware.frame.bean.BaseBean;

import java.util.Date;

public class BillStage extends BaseBean {
    private Integer id;

    private Integer billId;
    //分期编号
    private String billNo;
    //订单编号
    private Integer orderId;
    //月供
    private Float stageAmount;
    //分期本金
    private Float baseAmount;
    //分期服务费
    private Float chargeAmount;
    //最晚还款日
    private Date latestRepay;
    //逾期天数
    private Integer overdueDay;
    //0 新建 1 待还款 2 已还款 3 已逾期 4 已退货 5 已退款
    private Integer billStatus;
    //账单已还款金额
    private Float repayAmount;
    //描述
    private String description;
    //创建时间
    private Date createTime;
    //滞纳金
    private Float overdueFine;
    //实际还款日
    private Date actualRepay;
    //还款渠道:1支付宝 2财付通 3银商 4块钱 5 其他
    private Integer repayChannel;
    //删除标示:0,未删除；1，删除
    private Integer deleteFlag;
    //退货时间
    private Date returnTime;
    //退款时间
    private Date refundTime;
    //操作人
    private Integer updateUserid;
    //操作时间
    private Date updateTime;

    //增加当前期
    private String currentStage;
    
    private String billName;

    private Integer ownerUserid;

    private Integer payResult ;
    //增加产品名
    private String productName;
    //分期方案（共几期）
    private Integer stagePlan; 
    
    //交易流水号
    private String orderNo; 
    
    //系统当前时间， 解决数据库跟应用服务器时间不统一问题
    private Date sysCurrentTime;
    
    public Date getSysCurrentTime() {
		return sysCurrentTime;
	}

	public void setSysCurrentTime(Date sysCurrentTime) {
		this.sysCurrentTime = sysCurrentTime;
	}

	public Integer getStagePlan() {
		return stagePlan;
	}

	public void setStagePlan(Integer stagePlan) {
		this.stagePlan = stagePlan;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
        if(billNo!=null){
            try{
                this.billName = "第"+Integer.parseInt(billNo.substring(10))+"期";
                this.currentStage = billNo.substring(10);
            }catch (Exception ex){

            }
        }
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Float getStageAmount() {
        return stageAmount;
    }

    public void setStageAmount(Float stageAmount) {
        if( this.stageAmount!=null){
            this.stageAmount = stageAmount+ this.stageAmount;
        }else{
            this.stageAmount = stageAmount;
        }
    }

    public Float getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Float baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Float getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Float chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Date getLatestRepay() {
        return latestRepay;
    }

    public void setLatestRepay(Date latestRepay) {
        this.latestRepay = latestRepay;
    }

    public Integer getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(Integer overdueDay) {
        this.overdueDay = overdueDay;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Float getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Float repayAmount) {
        this.repayAmount = repayAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(Float overdueFine) {
        this.overdueFine = overdueFine;
        if(overdueFine!=null){
            if(this.stageAmount==null){
                this.stageAmount = overdueFine;
            }else{
                this.stageAmount = this.stageAmount+overdueFine;
            }
        }

    }

    public Date getActualRepay() {
        return actualRepay;
    }

    public void setActualRepay(Date actualRepay) {
        this.actualRepay = actualRepay;
    }

    public Integer getRepayChannel() {
        return repayChannel;
    }

    public void setRepayChannel(Integer repayChannel) {
        this.repayChannel = repayChannel;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public Integer getOwnerUserid() {
        return ownerUserid;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
        this.id = billId;
    }

    public void setOwnerUserid(Integer ownerUserid) {
        this.ownerUserid = ownerUserid;
    }

    public Integer getPayResult() {
        return payResult;
    }

    public void setPayResult(Integer payResult) {
        this.payResult = payResult;
    }

	public String getCurrentStage() {
		return currentStage;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


}
