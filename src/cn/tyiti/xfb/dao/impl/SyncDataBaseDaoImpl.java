/**
 * @(#)SyncDataBaseDaoImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.dao.SyncDataBaseDao;

/**
 * 同步用户数据到市场版Dao Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Repository("syncDataBaseDao")
public class SyncDataBaseDaoImpl extends BaseDao implements SyncDataBaseDao {

	/* （non-Javadoc）
	 * <p>Title: getMemberInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.MemberInfoDao#getMemberInfo(java.lang.Integer)
	 */
	@Override
	public List<SysUser> getUserInfo() throws Exception {
		// TODO Auto-generated method stub
		return (List<SysUser>) this.getSqlMapClientTemplate().queryForList("syncdatabase.getUserInfo");
	}
	
}
