/**
 * @(#)TradePasswordServiceImpl.java	1.0	2015-9-15
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.MemberInfo;
import cn.tyiti.xfb.dao.TradePasswordDao;
import cn.tyiti.xfb.service.TradePasswordService;

/**
 * 交易密码 Service Impl.
 * 
 * @version 1.0 2015-9-15
 * @author Black
 */
@Service
public class TradePasswordServiceImpl implements TradePasswordService {
	@Autowired
	private TradePasswordDao tradePasswordDao;
	/* （non-Javadoc）
	 * <p>Title: getMemberInfo</p>
	 * <p>Description: </p>
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.TradePasswordService#getMemberInfo(cn.tyiti.xfb.bojo.MemberInfo)
	 */
	@Override
	public MemberInfo getMemberInfo(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		return tradePasswordDao.getMemberInfo(memberInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: setTradePassword</p>
	 * <p>Description: </p>
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.TradePasswordService#setTradePassword(cn.tyiti.xfb.bojo.MemberInfo)
	 */
	@Override
	public int setTradePassword(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		return tradePasswordDao.setTradePassword(memberInfo);
	}

}
