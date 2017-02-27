/**
 * @(#)SyncDataBaseDao.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao;

import java.util.List;

import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.common.IBaseDao;

/**
 * 同步用户数据到市场版Dao.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public interface SyncDataBaseDao extends IBaseDao {
	/**
	 * 
	 * 获取用户信息.
	 * @author Black
	 * @date 2015-8-12 下午4:55:18
	 *
	 * @return
	 * @throws Exception
	 */
	List<SysUser> getUserInfo() throws Exception;
	
}
