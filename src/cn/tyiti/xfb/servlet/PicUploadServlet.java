/**
 * @(#)PicUploadServlet.java	1.0	2015-8-18
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.utils.AppUtils;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.MD5Util;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.common.exception.UploadFileException;
import cn.tyiti.xfb.service.ImageInfoService;
import cn.tyiti.xfb.utils.LogUtil;
import cn.tyiti.xfb.utils.MapUtil;
import cn.tyiti.xfb.utils.UploadFileUtil;

/**
 * 提升额度-上传图片.
 * 
 * @version 1.0 2015-8-18
 * @author Black
 */
public class PicUploadServlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 10000125844L;
	
	@Autowired
	private ImageInfoService imageInfoService;
	
    @Autowired
    private MemcachedClient memcachedClient;
	/**
	 * Constructor of the object.
	 */
	public PicUploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		//安全参数
		String authentication = request.getHeader("authentication");
		//用户ID
		String userId = request.getHeader("userId");
		//用户密码
		String password = request.getHeader("password");
		Map<String, String> authMap = AppUtils.splitPairValues(authentication);
		//秘钥
		String secretKey = authMap.get("secretKey");
		//时间戳
		String timestamp = authMap.get("timestamp");
		//返回参数
		BaseRspBean brb = new BaseRspBean();
		//输出打印前缀
		String prePrint = "------PicUploadServlet userId:"+userId+"------:";
		//空校验
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(password) 
				||StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(timestamp) ){
            brb.setCodeAndMsg("3001","请求头参数中存在空值");
            LogUtil.info(prePrint+"------请求头参数中存在空值------");
		} else {
			List<String> temp = new ArrayList<String>();
			temp.add("_");
			String requestRawValue = AppUtils.createRequestSignRawValue(request, temp);
			LogUtil.info(prePrint+"------requestRawValue------{" + requestRawValue + "}");
			
			String tempSecretKey = MD5Util.getMD5String(timestamp+"passw0rd!"+userId);
			//校验密钥
			boolean is_secret = secretKey.equals(tempSecretKey);
			//从缓存中校验用户名和密码 目前沟通结果是用户永远不会失效，所以暂时不考虑校验用户名和密码 后面会校验  
			//已校验 date:2015-08-25 author:Black
			String userKey = Constant.CACHE_USER_KEY + userId;
			SysUser sysUser = null;
			try {
				sysUser = (SysUser) memcachedClient.get(userKey);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				//从缓存中获取个人信息异常 从java内存中获取
				LogUtil.error(prePrint+ex.getMessage());
			}
			if(sysUser != null){
				boolean is_user = password.equalsIgnoreCase(sysUser.getPassWord()); 
				if(is_secret && is_user) {
					LogUtil.info(prePrint+"------success------");
					ImageInfo imageInfo = MapUtil.getParamConvertEntity(ImageInfo.class, request);
					try {
						//上传图片并返回图片路径
						List<String> picPathList= UploadFileUtil.picUploadHandle(request, imageInfo);
						//获取type
						String type = (String)request.getAttribute("type");
						if(StringUtils.isNotBlank(type)) {
							LogUtil.info(prePrint+"------上传图片的类型为------"+type);
							imageInfo.setType(type);
							imageInfo.setImagePath(picPathList.get(0));
			//				BaseRspBean brb = new BaseRspBean();
							imageInfoService.saveImageInfo(imageInfo);
							//成功设置
							brb.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
					                .get(Constant.SUCCESS_CODE));
						} else {
							// TODO Auto-generated catch block
				            brb.setCodeAndMsg("3005","上传图片的类型为空");
				            LogUtil.error(prePrint+"------上传图片的类型为空------");
						}
					} catch (UploadFileException ufex) {
						// TODO Auto-generated catch block
			            brb.setCodeAndMsg("3002","上传图片发生异常");
			            LogUtil.error(prePrint+"------上传图片发生异常------", ufex);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
			            brb.setCodeAndMsg("3003","保存图片数据发生异常");
			            LogUtil.error(prePrint+"------保存图片发生异常------", ex);
					}
				} else{
		            //非法请求
		            //非法设置
		            brb.setCodeAndMsg("6777","已在另一台设备上更改密码，请重新登录");
		            LogUtil.info(prePrint+"------已在另一台设备上更改密码，请重新登录------");
		        }
			} 
			else {
	            //重新登录提示
	            brb.setCodeAndMsg("6789","请重新登录");
				LogUtil.info(prePrint+"------请重新登录------");
			}
		}
		/*Map paramMap = new HashMap();
		paramMap.put(Constant.RETURN_MESSAGE, brb);*/
		String result = JsonUtils.getJSONString(brb);
		response.getWriter().write(result);
		LogUtil.info(prePrint+"------result------"+result);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
	}

}
