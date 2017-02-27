package cn.emagsoftware.xfb.dto;

import java.util.HashMap;
import java.util.Map;

import cn.emagsoftware.frame.bean.BaseRspBean;

public class SingleOrderBillRsp extends BaseRspBean{
	private Map<String, Object> SingleOrderBill = new HashMap<String, Object>();

	public Map<String, Object> getSingleOrderBill() {
		return SingleOrderBill;
	}

	public void setSingleOrderBill(Map<String, Object> singleOrderBill) {
		SingleOrderBill = singleOrderBill;
	}
	

}
