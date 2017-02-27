/**
 * @(#)PromoteAmountServiceImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.PromoteAmount;
import cn.tyiti.xfb.bojo.PromoteCommon;
import cn.tyiti.xfb.dao.PromoteAmountDao;
import cn.tyiti.xfb.service.PromoteAmountService;

/**
 * 提升额度-获取数据Service Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Service
public class PromoteAmountServiceImpl implements PromoteAmountService {

	@Autowired
	private PromoteAmountDao promoteAmountDao;
	/* （non-Javadoc）
	 * <p>Title: getStateInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.PromoteAmountService#getStateInfo(java.lang.Integer)
	 */
	@Override
	public PromoteAmount getStateInfo(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		PromoteAmount stateInfo = promoteAmountDao.getStateInfo(userId);
		/*针对前段返回null处理*/
		PromoteCommon pc = new PromoteCommon();
		if(stateInfo.getContactsInfo() == null){
			stateInfo.setContactsInfo(pc);
		}
		if(stateInfo.getCreditCard() == null){
			stateInfo.setCreditCard(pc);
		}
		if(stateInfo.getDepositCard() == null){
			stateInfo.setDepositCard(pc);
		}
		if(stateInfo.getDrivingLicense() == null){
			stateInfo.setDrivingLicense(pc);
		}
		if(stateInfo.getMemberInfo() == null){
			stateInfo.setMemberInfo(pc);
		}
		if(stateInfo.getJobInfo() == null){
			stateInfo.setJobInfo(pc);
		}
		if(stateInfo.getStudentInfo() == null){
			stateInfo.setStudentInfo(pc);
		}
		return stateInfo;
	}

}
