package cn.tyiti.xfb.bojo;

/**
 * 手机相关参数vo
 * @author shenwu
 * @date 2015年11月30日10:33:22
 */
public class CellphoneParameterInfo{
	
	//主键Id
	private Integer id;
	
	//用户Id
	private String userId;
	
	//手机唯一码
	private String uuid;
	
	//操作系统版本
	private String OSversion;
	
	//手机型号
	private String phoneModel;
	
	//手机运营商
	private String carrierName;
	
	//创建时间
	private String creatTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOSversion() {
		return OSversion;
	}

	public void setOSversion(String oSversion) {
		OSversion = oSversion;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

}
