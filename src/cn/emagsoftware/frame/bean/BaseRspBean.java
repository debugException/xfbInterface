package cn.emagsoftware.frame.bean;

/**
 * @Title: 返回结果通知BEAN
 */
public class BaseRspBean {

	/** 通知代码 */
	protected String resultCode;

	/** 通知内容 */
	protected String resultMessage;

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
	
	public void setCodeAndMsg(String code,String msg){
		resultCode = code;
		resultMessage = msg;
	}
}
