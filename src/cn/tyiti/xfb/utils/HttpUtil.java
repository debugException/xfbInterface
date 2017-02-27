package cn.tyiti.xfb.utils;   

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/** 
 * 创建时间：2015-12-3 上午9:24:51  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：HttpUtil.java  
 * 类说明：  
 */
public class HttpUtil {
	
	private  static Logger log = Logger.getLogger(HttpUtil.class);
	/**
	 * <p>Title:sendHttpPostRequest</p>
	 * <p>Description: post 请求</p>
	 * @param url 访问地址
	 * @param param 数组
	 * @return response json字符串
	 * @throws IOException
	 */
	public static String sendHttpPostRequest(String url,NameValuePair[] param ) throws IOException{
        HttpClient httpClient = new HttpClient();  
        HttpMethod method = postMethod(url,param);  
        
        httpClient.executeMethod(method); 
        StringBuffer response = new StringBuffer();  
        if (method.getStatusCode() == HttpStatus.SC_OK) {  
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));  
            String line;  
            while ((line = reader.readLine()) != null) {  
                    if (true)  
                            response.append(line).append(System.getProperty("line.separator"));  
                    else  
                            response.append(line);  
            }  
            reader.close();  
        } 
       // String response = method.getResponseBodyAsString();  
        //String response = new String(method.getResponseBodyAsString().getBytes("ISO-8859-1"));                  
        return response.toString() ;
	 }  
	/**
	 * <p>Title:sendHttpGetRequest</p>
	 * <p>Description:</p>
	 * @param url url
	 * @param param 参数  用& 拼接，例：name=xiaoming&sex=男
	 * @return
	 * @throws IOException
	 */
	public static String sendHttpGetRequest(String url, String param)
			throws IOException {
		HttpClient httpClient = new HttpClient();
		HttpMethod method = getMethod(url, param);

		httpClient.executeMethod(method);
		StringBuffer response = new StringBuffer();
		if (method.getStatusCode() == HttpStatus.SC_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream(), "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				if (true)
					response.append(line).append(
							System.getProperty("line.separator"));
				else
					response.append(line);
			}
			reader.close();
		}

		// String response = method.getResponseBodyAsString();
		// String response = new
		// String(method.getResponseBodyAsString().getBytes("ISO-8859-1"));
		return response.toString();
	}  
	 /**
	  * <p>Title:getMethod</p>
	  * <p>Description:get请求方法 </p>
	  * @param url url 
	  * @param param 参数 
	  * @return HttpMethod 方法
	  * @throws IOException
	  */
	public static HttpMethod getMethod(String url, String param)
			throws IOException {
		GetMethod get = new GetMethod(url + "?" + param);
		get.releaseConnection();
		return get;
	}  
   /**
    * <p>Title:postMethod</p>
    * <p>Description:获取post方法</p>
    * @param url  url 
    * @param param 数组NameValuePair[]，requestBody参数
    * @return HttpMethod  post方法 
    * @throws IOException
    */
	public static HttpMethod postMethod(String url, NameValuePair[] param)
			throws IOException {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		post.setRequestBody(param);
		post.releaseConnection();
		return post;
	}
	
	
	/**
	 * 通过 get请求url
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.error("发送请求地址"+url+"异常信息："+e) ;
			System.out.println("发送GET请求出现异常！" + e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		log.info("result---->>" + result) ;
		return result;
	}

	/*
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url 发送请求的 URL
	 * 
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * 
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			log.error("发送请求地址:"+url+"param:"+param+"异常信息："+e) ;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
}
 