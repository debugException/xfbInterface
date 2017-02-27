/**
 * @(#)PromoteAmountDaoImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.tyiti.xfb.bojo.PromoteAmount;
import cn.tyiti.xfb.dao.PromoteAmountDao;

/**
 * 提升额度-获取数据 Dao Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Repository("promoteAmountDao")
public class PromoteAmountDaoImpl extends BaseDao implements PromoteAmountDao {

	/* （non-Javadoc）
	 * <p>Title: getStateInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.PromoteAmountDao#getStateInfo(java.lang.Integer)
	 */
	@Override
	public PromoteAmount getStateInfo(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		return (PromoteAmount) this.getSqlMapClientTemplate().queryForObject("promoteamount.getStateInfo", userId);
	}
	
}
