package cn.emagsoftware.xfb.dto;

import java.util.HashMap;
import java.util.Map;

import cn.emagsoftware.frame.bean.BaseRspBean;

public class BillDetailRsp extends BaseRspBean {
	private Map<String, String> map = new HashMap();

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	
}
