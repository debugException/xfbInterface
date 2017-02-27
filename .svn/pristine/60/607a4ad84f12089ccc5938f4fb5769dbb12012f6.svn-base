/**
 * @(#)TradePasswordDaoImpl.java	1.0	2015-9-15
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.MemberInfo;
import cn.tyiti.xfb.dao.TradePasswordDao;

/**
 * 交易密码Dao Impl
 * 
 * @version 1.0 2015-9-15
 * @author Black
 */
@Repository
public class TradePasswordDaoImpl extends BaseDao implements TradePasswordDao {
	/* （non-Javadoc）
	 * <p>Title: getMemberInfo</p>
	 * <p>Description: </p>
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.TradePasswordDao#getMemberInfo(cn.tyiti.xfb.bojo.MemberInfo)
	 */
	@Override
	public MemberInfo getMemberInfo(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		return (MemberInfo) this.getSqlMapClientTemplate().queryForObject("tradepassword.getMemberInfo", memberInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: setTradePassword</p>
	 * <p>Description: </p>
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.TradePasswordDao#setTradePassword(cn.tyiti.xfb.bojo.MemberInfo)
	 */
	@Override
	public int setTradePassword(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("tradepassword.setTradePassword", memberInfo);
	}

}
