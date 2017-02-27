package cn.tyiti.xfb.utils.ybtc;   

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.NameValuePair;

/** 
 * 创建时间：2015-12-1 上午9:00:16  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：BlackUtil.java  
 * 类说明：  
 */
public class BlackUtil {
	/**
	 * <p>Title:getMd5Url</p>
	 * <p>Description:加密 url</p>
	 * @param url
	 * @return 加密后 url
	 */
	public static String getMd5Url(String url) {
/*		String appId = PropertiesUtil.getInstance().getAppId_ybtc();
		String tockenId = PropertiesUtil.getInstance().getTockenId_ybtc();*/
		 String appId = BlackUtil.getPropertiesValueByKey("appId_ybtc");
		 String tockenId = BlackUtil.getPropertiesValueByKey("tockenId_ybtc");
		String md5Url = MD5Util_YBTC.md5(url + appId + tockenId);
		return md5Url;
	}
	
	 /**
	  * <p>Title:getPropertiesValueByKey</p>
	  * <p>Description:通过 key 获取value</p>
	  * @param keyString key值
	  * @return value
	  */
	public static String getPropertiesValueByKey(String keyString) {
		InputStreamReader inputStream = null ;
		
		String configFile = BlackUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "") + "config.properties";  
		Properties prop = new Properties();// 属性集合对象
        try {  
             inputStream = new InputStreamReader(new FileInputStream(configFile), "utf-8");  
            prop.load(inputStream);  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } // 使用finally块来关闭输入流
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} 
		
		return prop.getProperty(keyString);
	}
	 /**
	  * <p>Title:getUrl</p>
	  * <p>Description:</p>
	  * @param bankCardNumber	 银行卡号
	  * @param idCardNo 		身份证号
	  * @param name 			姓名 
	  * @return 				拼接加密的url
	  */
	 public static String getBankCardUrl(String bankCardNumber,String idCardNo,String name){
		 String url_BankCard = BlackUtil.getPropertiesValueByKey("url_BankCard");
		 String appId = BlackUtil.getPropertiesValueByKey("appId_ybtc");
		 String urls = url_BankCard+"?bankCardNumber="+bankCardNumber+"&idCardNo="+idCardNo+"&name="+name;
		 String md5Url = BlackUtil.getMd5Url(urls);
		 String url = urls+"&appId="+appId+"&tockenKey="+md5Url;
		 return url;
	 }
	 /**
	  * <p>Title:getIndentityCard</p>
	  * <p>Description:身份证认证url获取</p>
	  * @param name 姓名
	  * @param idCardNo 身份证号
	  * @return 加密后的url
	  */
	 public  static String getIndentityCardUrl(String idCardNo,String name){
		 String url_IndentityCard = BlackUtil.getPropertiesValueByKey("url_IndentityCard");
		 String appId = BlackUtil.getPropertiesValueByKey("appId_ybtc");
		 String urls = url_IndentityCard+"?idCardNo="+idCardNo+"&name="+name;
		 String md5Url = BlackUtil.getMd5Url(urls);
		 String url = urls+"&appId="+appId+"&tockenKey="+md5Url;
		 return url;
	 }
	 
	 public  static NameValuePair[] getDriverVerifUrl(String xm,String jszh){
		// String url = "http://api.ypcredit.com/driver/status/driver";
		 
		 String url = BlackUtil.getPropertiesValueByKey("url_IndentityCard");
		 //String appId = "444d1e1e-84f5-4946-8fa5-31ca3348ea8c" ;
		 //String tockenId = "cc9a5ec8-686e-4018-803e-e3c4821503f9";
		// String jszh = "22040219621612641X";
		 //String xm = "钱文";
		// String  md5tockenKey = MD5Util_YBTC.md5(url+appId+tockenId);
		 
		 String appId = BlackUtil.getPropertiesValueByKey("appId_ybtc");
		 String tockenId = BlackUtil.getPropertiesValueByKey("tockenId_ybtc");
		 
		 String  md5tockenKey = getMd5Url(url);
		 
		 Map<String, String> mp = new HashMap<String, String>() ;
		 mp.put("param:appId", appId.trim());
		 mp.put("param:tockenKey", md5tockenKey.trim());
		 mp.put("param:jszh", jszh);
		 mp.put("param:xm",xm);
		 NameValuePair[] param = { new NameValuePair("param:appId",appId.trim()),  
				 new NameValuePair("param:tockenKey",md5tockenKey.trim()),  
				 new NameValuePair("param:jszh",jszh),  
				 new NameValuePair("param:xm",xm) } ;
		 
		 return param ;
	 }
	
}
 