package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.SysUser;

public class CustomerRegistRsp extends BaseRspBean {

    private String customerID;



    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }




}
