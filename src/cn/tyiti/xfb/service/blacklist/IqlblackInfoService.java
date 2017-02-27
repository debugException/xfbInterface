package cn.tyiti.xfb.service.blacklist;   

import java.util.Map;

/** 
 * 创建时间：2015-11-24 下午9:51:55  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：QlblackInfoService.java  
 * 类说明：  
 */
public interface IqlblackInfoService {
	/**
	 * <p>Title:insertQlblackInfo</p>
	 * <p>Description:</p>
	 * @param params resultDoc document文档，  serviceCode接口名称， name姓名， idcode 身份证号， userId， loginName 手机号
	 * @throws Exception
	 */
	void insertQlblackInfo(Map<String, Object> params)throws Exception;
}
 