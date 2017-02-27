package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.Customer;

/**
 * 会员
 *
 */
public class CustomerRsp extends BaseRspBean {

    private Customer customer;


    private Integer userId;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
