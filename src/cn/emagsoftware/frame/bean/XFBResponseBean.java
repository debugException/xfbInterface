package cn.emagsoftware.frame.bean;

import net.sf.json.JSONObject;

/**
 * @author 0
 *
 */
public class XFBResponseBean {

	private String resultCode;
	private String resultMessage;
	private Object data;

	/**
	 * @param resultCode
	 * @param resultMessage
	 * @param data
	 */
	public XFBResponseBean(String resultCode, String resultMessage, Object data) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.data = data;
		// / 防止json 出现null
		if (data == null || data.equals("")) {
			this.data = new JSONObject();
		}
	}

	/**
	 * @param resultCode
	 * @param resultMessage
	 */
	public XFBResponseBean(String resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		// 防止为空
		data = new JSONObject();
	}

	public XFBResponseBean() {
		// / 防止json 出现null
		if (data == null || data.equals("")) {
			data = new JSONObject();
		}
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
