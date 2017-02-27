package cn.tyiti.xfb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * 读取配置文件静态数据工具类,该类数据修改配置文件后需重启后才生效
 * */
public class PropertiesUtil {
	private Logger  log=Logger.getLogger(PropertiesUtil.class); 
	
	private PropertiesUtil(){};
	
	private static PropertiesUtil properties;
	
	public static PropertiesUtil getInstance(){
		if(properties == null){
			properties =  new PropertiesUtil();
		}
		return properties;
	}
	
	private static String webServiceUrl; 			//webService地址
	private static String webServiceUserName; 		//webService用户名
	private static String webServicepassword; 		//webService密码
	private static String deleteUserHttpUrl;	//冻结用户调用的Interface的接口地址
	private static String resetPwdHttpUrl;		//重置密码调用的Interface的接口地址
	private static String url_ybtc;		//易宝天创url
	private static String appId_ybtc;		//易宝天创appId
	private static String tockenId_ybtc;		//易宝天创tockenId
//	private static String deliveryHttpUrl;		//订单确认收获调用的Interface的接口地址
//	private static String updateOrderHttpUrl;	//修改订单状态调用的Interface的接口地址
	private static String initialPassword;		//重置的密码
	private static String resourcePath;			//图片存放根目录
	
	/**
	 * 获取图片存放根目录
	 * */
	public String getResourcePath(){
		if(resourcePath == null){
			resourcePath = getInstance().getProperties().getProperty("resource.tomcat.path");
		}
		return resourcePath;
	}
	
//	/**
//	 * 订单确认收货
//	 * */
//	public String getDeliveryHttpUrl(){
//		if(deliveryHttpUrl == null){
//			deliveryHttpUrl = getInstance().getProperties().getProperty("delivery_httpUrl");
//		}
//		return deliveryHttpUrl;
//	}
//	
//	/**
//	 * 修改订单状态
//	 * */
//	public String getUpdateOrderHttpUrl(){
//		if(updateOrderHttpUrl == null){
//			updateOrderHttpUrl = getInstance().getProperties().getProperty("updateOrder_httpUrl");
//		}
//		return updateOrderHttpUrl;
//	}
	
	
	/**
	 * url_ybtc
	 * */
	public String getUrl_ybtc(){
		if(url_ybtc == null){
			url_ybtc = getInstance().getProperties().getProperty("url_ybtc");
		}
		return url_ybtc;
	}
	/**
	 * appId
	 * */
	public String getAppId_ybtc(){
		if(appId_ybtc == null){
			appId_ybtc = getInstance().getProperties().getProperty("appId_ybtc");
		}
		return appId_ybtc;
	}
	/**
	 * tockenId
	 * */
	public String getTockenId_ybtc(){
		if(tockenId_ybtc == null){
			tockenId_ybtc = getInstance().getProperties().getProperty("tockenId_ybtc");
		}
		return tockenId_ybtc;
	}
	/**
	 * 重置的密码
	 * */
	public String getInitialPassword(){
		if(initialPassword == null){
			initialPassword = getInstance().getProperties().getProperty("initial_password");
		}
		return initialPassword;
	}
	
	/**
	 * 重置密码调用的Interface的接口地址
	 * */
	public String getResetPwdHttpUrl(){
		if(resetPwdHttpUrl == null){
			resetPwdHttpUrl = getInstance().getProperties().getProperty("resetPwd_httpUrl");
		}
		return resetPwdHttpUrl;
	}
	
	/**
	 * 冻结用户调用的Interface的接口地址
	 * */
	public String getDeleteUserHttpUrl(){
		if(deleteUserHttpUrl == null){
			deleteUserHttpUrl = getInstance().getProperties().getProperty("deleteUser_httpUrl");
		}
		return deleteUserHttpUrl;
	}
	
	/**
	 * 获取webService地址
	 * */
	public String getWebServiceUrl(){
		if(webServiceUrl == null){
			webServiceUrl = getInstance().getProperties().getProperty("webServiceUrl");
		}
		return webServiceUrl;
	}
	/**
	 * <p>Title:getUserName91</p>
	 * <p>Description:获取91征信用户名</p>
	 * @return
	 */	
	public String getWebServiceUserName(){
		if(webServiceUserName == null){
			webServiceUserName = getInstance().getProperties().getProperty("webServiceUserName");
		}
		return webServiceUserName;
		
		
	}
	/**
	 * <p>Title:getPassWord91</p>
	 * <p>Description:获取91征信密码</p>
	 * @return
	 */
	public String getWebServicepassword(){
		if(webServicepassword == null){
			webServicepassword = getInstance().getProperties().getProperty("webServicepassword");
		}
		return webServicepassword;
		
	}
	
	/**
	 * 获取配置文件
	 * @throws IOException 
	 * */
	private Properties getProperties() {
		// 生成文件输入流
		InputStream inpf = null;
		Properties p = null;
		try {
			try{
				p = new Properties();
				inpf = PropertiesUtil.class.getClassLoader().getResourceAsStream("/config.properties");
//				File file = new File(System.getProperty("user.dir")+"/src/config.properties");
//				inpf = new FileInputStream(file);
				p.load(inpf);
			}finally{
				if(inpf != null)
					inpf.close();
			}
		}catch (IOException e) {
			log.error("获取配置文件config.properties失败!");
		}
		return p;
	}
	
}
