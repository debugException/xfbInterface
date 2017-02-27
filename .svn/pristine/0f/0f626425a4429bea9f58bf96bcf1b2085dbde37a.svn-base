package cn.emagsoftware.xfb.pojo;

import cn.emagsoftware.frame.bean.BaseBean;

import java.util.Date;
import java.util.List;

public class OrderInfo  extends BaseBean{
    private Integer id;

    private String orderName;

    private String productName;

    private Float productPrice;

    private String shopName;

    private Float useLimit;

    private Integer orderStatus;

    private Integer createUserid;

    private Date createTime;
    
    private Date dealTime;

    private Integer updateUserid;


    private Integer ownerUserid;

    private Date updateTime;

    private String description;


    private Date createStartTime;

    private Date createEndTime;
    
    private String returnCause;

    private int stageNum;
    /* 2015/7/29新增 账单首页逻辑处理 */
    BillStage billStage;
    
    /*List<StageRate> stageList ;

    List<BillStage> billStageList;*/

    private Integer stagePlan;

    private float allStageAmount;
    
    private Integer deleteFlag;
    
    //全部订单  或者  部分 订单 标志位
    //帐单时 null:全部账单    A1:全部账单    A2:近7日待还账单
    //订单时:全部订单    A1:全部订单    A2:待确认订单(取订单状态10 待交货、 11已交货(待收货))
    private String flag; 
    
    /**
     * 订单审批状态 0-待审批 1-审批通过 2-拒绝 
     * */
    private Integer verifyState;
    
    
    
    public Integer getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(Integer verifyState) {
		this.verifyState = verifyState;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getReturnCause() {
		return returnCause;
	}

	public void setReturnCause(String returnCause) {
		this.returnCause = returnCause;
	}

	public float getAllStageAmount() {
		return allStageAmount;
	}

	public void setAllStageAmount(float allStageAmount) {
		this.allStageAmount = allStageAmount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }



    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Float getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Float useLimit) {
        this.useLimit = useLimit;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public void setOwnerUserid(Integer ownerUserid) {
        this.ownerUserid = ownerUserid;
    }
    public Integer getOwnerUserid() {
        return ownerUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    /*public List<StageRate> getStageList() {
        return stageList;
    }

    public void setStageList(List<StageRate> stageList) {
        this.stageList = stageList;
    }

    public List<BillStage> getBillStageList() {
        return billStageList;
    }

    public void setBillStageList(List<BillStage> billStageList) {
        this.billStageList = billStageList;
    }*/

    public Integer getStagePlan() {
        return stagePlan;
    }

    public void setStagePlan(Integer stagePlan) {
        this.stagePlan = stagePlan;
    }

	public BillStage getBillStage() {
		return billStage;
	}

	public void setBillStage(BillStage billStage) {
		this.billStage = billStage;
	}
    
}
