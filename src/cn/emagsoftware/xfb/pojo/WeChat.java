package cn.emagsoftware.xfb.pojo;


/**
*      
* 项目名称：xfbInterface     
* 类名称：WeChat     
* 类描述： 微信返回信息    
* 创建人：shenzhiqiang    
* 创建时间：2015-11-16 下午5:05:19       
* @version      
*
 */
public class WeChat {
	
	private String appid;  //公众账号ID
	
	private String sign;  //签名
	
	private String timestamp;  //时间戳
	
	private String noncestr;  //随机字符串
	
	private String partnerid;  //合作伙伴
	
	private String status;
	
	private String prepayid;  //预支付交易id
	
	private String packages;
	
	private String errMesg;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public String getErrMesg() {
		return errMesg;
	}

	public void setErrMesg(String errMesg) {
		this.errMesg = errMesg;
	}

	
}
