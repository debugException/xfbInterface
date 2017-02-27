package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.emagsoftware.xfb.pojo.OrderInfo;
import cn.emagsoftware.xfb.pojo.StageRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-21
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class OrderStagingRateRsp  extends BaseRspBean{

   private int orderId;

    private String orderName;

    private Float useLimit;



    private Integer billTotal;

    private Integer page;

    private OrderInfo orderInfo;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Float getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Float useLimit) {
        this.useLimit = useLimit;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }



    public Integer getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(Integer billTotal) {
        this.billTotal = billTotal;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
