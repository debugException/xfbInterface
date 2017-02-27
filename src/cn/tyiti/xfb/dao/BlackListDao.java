package cn.tyiti.xfb.dao;

import java.util.List;

import cn.tyiti.xfb.bojo.UserInfo;

public interface BlackListDao {

/**
 * 根据userid查询用户信息
 * @param userId
 * @return
 * @throws Exception
 */
	UserInfo getUserInfoByUserId(Integer userId)throws Exception;
/**
 * 主表插入数据
 * @param userInfo
 * @return
 * @throws Exception
 */
	void addData(UserInfo userInfo)throws Exception;
/**
 * 
 * @return
 * @throws Exception
 */
	List<UserInfo> getUserInfo()throws Exception;

	void batchInsert(List<UserInfo> list)throws Exception;

}
