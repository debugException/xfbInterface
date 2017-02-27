package cn.emagsoftware.xfb.dto;

import net.sf.json.JSONObject;
import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * 订单信息
 *
 */
public class OrderConfirmRsp extends BaseRspBean {

	private Object data;
	/*是否设置交易密码
	0：已设置
	1：未设置
	 */
	private String isTradePassword;
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		// / 防止json 出现null
		if (data == null || data.equals("")) {
			this.data = new JSONObject();
		}
	}

	public String getIsTradePassword() {
		return isTradePassword;
	}

	public void setIsTradePassword(String isTradePassword) {
		this.isTradePassword = isTradePassword;
	}

	
}
