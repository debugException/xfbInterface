package cn.tyiti.xfb.service;

import java.util.List;

import cn.tyiti.xfb.bojo.UserInfo;

public interface BlackListService {

	/**
	 * 根据用户userid查询是否被自动决绝
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UserInfo getUserInfoByUserId(Integer userId)throws Exception;
/**
 * 插入主表
 * @param userInfo
 * @return
 * @throws Exception
 */
	void addData(UserInfo userInfo)throws Exception;
	/**
	 * 获取所有授信用户信息
	 * @return
	 * @throws Exception
	 */
	List<UserInfo> getUserInfo()throws Exception;
	/**
	 * 查询并插入黑名单主表
	 * @param list 
	 */
	void queryAndInsert(List<UserInfo> list)throws Exception;
}
