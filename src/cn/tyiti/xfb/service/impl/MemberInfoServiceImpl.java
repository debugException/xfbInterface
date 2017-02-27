/**
 * @(#)MemberInfoServiceImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tyiti.xfb.bojo.CellphoneParameterInfo;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.bojo.MemberInfo;
import cn.tyiti.xfb.bojo.ProfessionInfo;
import cn.tyiti.xfb.bojo.VerifyLogInfo;
import cn.tyiti.xfb.dao.MemberInfoDao;
import cn.tyiti.xfb.service.MemberInfoService;

/**
 * 提升额度-获取个人信息/提升额度-提交个人信息Service Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Service("memberInfoServiceII")
public class MemberInfoServiceImpl implements MemberInfoService {

	@Autowired
	private MemberInfoDao memberInfoDao;
	/* （non-Javadoc）
	 * <p>Title: getStateInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.PromoteAmountService#getStateInfo(java.lang.Integer)
	 */
	@Override
	public MemberInfo getMemberInfo(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		return memberInfoDao.getMemberInfo(memberInfo);
	}
	
	/* （non-Javadoc）
	 * <p>Title: saveMemberInfo</p>
	 * <p>Description: </p>
	 * @param memberInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.MemberInfoService#saveMemberInfo(cn.tyiti.xfb.bojo.MemberInfo)
	 */
	@Override
	public int saveMemberInfo(MemberInfo memberInfo) throws Exception {
		// TODO Auto-generated method stub
		if("A2".equals(memberInfo.getVerifyState())){
			/*会员信息审批状态变更为A3(审核中)
			用户表中verify_state设置为A1(新进)；has_verify设置为A1(需要)*/
			int userId = memberInfo.getUserId();
			memberInfo.setVerifyState("A3");
			memberInfoDao.updateUserState(userId);
			//更新图片状态为审核中
			ImageInfo imageInfo = new ImageInfo();
			imageInfo.setUserId(userId);
			imageInfo.setType("'A1','B1','C1'");
//			imageInfo.setlSize(3);
			//更新图片状态为审核中
			memberInfoDao.updateImageVerifyState(imageInfo);
		} else {
			//审批状态为草稿
			memberInfo.setVerifyState("A1");
		}
		//保存会员信息
		int result = memberInfoDao.saveMemberInfo(memberInfo);
		
		return result;
	}
	
	//兼容V1.1.4
	@Override
	public int saveMembersInfo(MemberInfo memberInfo) throws Exception {
		if("A2".equals(memberInfo.getVerifyState())){
			/*会员信息审批状态变更为A3(审核中) 用户表中verify_state设置为A1(新进)；has_verify设置为A1(需要)*/
			int userId = memberInfo.getUserId();
			memberInfo.setVerifyState("A3");
			memberInfoDao.updateUserState(userId);
			ImageInfo imageInfo = new ImageInfo();
			imageInfo.setUserId(userId);
			imageInfo.setType("'A1','B1','C1'");
			memberInfoDao.updateImageVerifyState(imageInfo);//更新图片状态为审核中
		} else {
			memberInfo.setVerifyState("A1");//审批状态为草稿
		}
		int result = memberInfoDao.saveMembersInfo(memberInfo);//保存会员信息
		return result;
	}
	
	/**
	 * 保存头信息，手机相关参数
	 */
	@Override
	public int saveCellphoneParameterInfo(CellphoneParameterInfo cellphoneParameterInfo) throws Exception {
		int result = memberInfoDao.saveCellphoneParameterInfo(cellphoneParameterInfo);
		return result;
	}
	
	
	/**
	 * 名称：getPartnerBaseInfo <br/>
	 * 描述：根据用户ID查询授信拦截地址（商户地址） <br/>
	 * @author shenwu
	 * @date 2015年11月23日15:39:35
	 * @return MemberInfo.city		常住市
	 */
	@Override
	public ProfessionInfo getPartnerBaseInfo(String uId) throws Exception {
		return memberInfoDao.getPartnerBaseInfo(uId);
	}
	
	/**
	 * 地址不相同增加审核日志
	 * @param verifyLogInfo 审核日志信息
	 * @author shenwu
	 * @throws Exception
	 */
	@Override
	public int saveVerifyLogInfo(VerifyLogInfo verifyLogInfo) throws Exception {
		int result = memberInfoDao.saveVerifyLogInfo(verifyLogInfo);
		return result;
	}

	/**
	 * 自动拦截之后，修改用户状态
	 * @param uId
	 * @throws Exception
	 */
	@Override
	public int updateUserStatus(Integer uId) throws Exception {
		return memberInfoDao.updateUserStatus(uId);
	}
	
}
