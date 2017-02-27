/**
 * @(#)ProfessionInfoServiceImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.WcfCallUtils;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.bojo.JobInfo;
import cn.tyiti.xfb.bojo.StudentInfo;
import cn.tyiti.xfb.dao.ProfessionInfoDao;
import cn.tyiti.xfb.service.ProfessionInfoService;

/**
 * 提升额度-获取职业信息/提升额度-提交职业信息Service Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Service
public class ProfessionInfoServiceImpl implements ProfessionInfoService {

	@Autowired
	private ProfessionInfoDao professionInfoDao;
    @Autowired
    private MemcachedClient memcachedClient;
    @Autowired
    private WcfCallUtils wcfCallUtils;
    
	/* （non-Javadoc）
	 * <p>Title: getStateInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.PromoteAmountService#getStateInfo(java.lang.Integer)
	 */
	@Override
	public JobInfo getProfessionInfo(JobInfo jobInfo) throws Exception {
		// TODO Auto-generated method stub
		return professionInfoDao.getProfessionInfo(jobInfo);
	}
	
	/* （non-Javadoc）
	 * <p>Title: saveProfessionInfo</p>
	 * <p>Description: </p>
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.ProfessionInfoService#saveProfessionInfo(cn.tyiti.xfb.bojo.ProfessionInfo)
	 */
	@Override
	public int saveProfessionInfo(JobInfo jobInfo) throws Exception {
		
		// TODO Auto-generated method stub
		int result = 0;
		//插入标识 true :插入 fasle ：更新
		boolean tflag = false;
		/*
		 * 验证id是否为空处理
		 * 为空：插入
		 * 非空：更新
		 */
		if(StringUtils.isEmpty(jobInfo.getId()) || jobInfo.getId() == 0) {
			JobInfo professionInfo = professionInfoDao.getProfessionInfo(jobInfo);
			if(professionInfo==null){
				tflag = true;
			} else {
				jobInfo.setId(professionInfo.getId());
			}
		}
		if(tflag){
			Integer userId = jobInfo.getUserId();
			if(StringUtils.isEmpty(jobInfo.getVerifyState())){
				//审批状态为草稿
				jobInfo.setVerifyState("A1");
			}
			//缓存用户唯一标识
			String userKey = Constant.CACHE_USER_KEY + userId;
			//从缓存中获取用户信息
			SysUser sysUser = (SysUser) memcachedClient.get(userKey);
			result = professionInfoDao.insertProfessionInfo(jobInfo);
//				调用市场版返回成功后在插入数据
			wcfCallUtils.callSetMemberType(userId, sysUser.getLoginName(), -5);
		} else {
			Integer userId = jobInfo.getUserId();
			// TODO Auto-generated method stub
			if("A2".equals(jobInfo.getVerifyState())){
				/*职业信息审批状态变更为A3(审核中)
				用户表中verify_state设置为A1(新进)；has_verify设置为A1(需要)*/
				jobInfo.setVerifyState("A3");
				professionInfoDao.updateUserState(userId);
			} else {
				//审批状态为草稿
				jobInfo.setVerifyState("A1");
			}
			//保存职业信息
			result = professionInfoDao.saveProfessionInfo(jobInfo);
		}
		//更新用户状态为上班族
		SysUser sysUser = new SysUser();
		sysUser.setId(jobInfo.getUserId());
		sysUser.setType("A1");
		professionInfoDao.updateUserInfoByUserId(sysUser);
		return result;
	}

	/* （non-Javadoc）
	 * <p>Title: getStateInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.PromoteAmountService#getStateInfo(java.lang.Integer)
	 */
	@Override
	public StudentInfo getProfessionInfo(StudentInfo studentInfo) throws Exception {
		// TODO Auto-generated method stub
		return professionInfoDao.getProfessionInfo(studentInfo);
	}
	
	/* （non-Javadoc）
	 * <p>Title: saveProfessionInfo</p>
	 * <p>Description: </p>
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.ProfessionInfoService#saveProfessionInfo(cn.tyiti.xfb.bojo.ProfessionInfo)
	 */
	@Override
	public int saveProfessionInfo(StudentInfo studentInfo) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		//插入标识 true :插入 fasle ：更新
		boolean tflag = false;
		/*
		 * 验证id是否为空处理
		 * 为空：插入
		 * 非空：更新
		 */
		if(StringUtils.isEmpty(studentInfo.getId()) || studentInfo.getId() == 0) {
			StudentInfo professionInfo = professionInfoDao.getProfessionInfo(studentInfo);
			if(professionInfo==null){
				tflag = true;
			} else {
				studentInfo.setId(professionInfo.getId());
			}
		}
		if(tflag){
			Integer userId = studentInfo.getUserId();
			if(StringUtils.isEmpty(studentInfo.getVerifyState())){
				//审批状态为草稿
				studentInfo.setVerifyState("A1");
			}
			//缓存用户唯一标识
			String userKey = Constant.CACHE_USER_KEY + userId;
			//从缓存中获取用户信息
			SysUser user = (SysUser) memcachedClient.get(userKey);
			result = professionInfoDao.insertProfessionInfo(studentInfo);
//				调用市场版返回成功后在插入数据
			wcfCallUtils.callSetMemberType(userId, user.getLoginName(), -1);
		} else {
			Integer userId = studentInfo.getUserId();
			if("A2".equals(studentInfo.getVerifyState())){
				/*学生信息审批状态变更为A3(审核中)
				用户表中verify_state设置为A1(新进)；has_verify设置为A1(需要)*/
				studentInfo.setVerifyState("A3");
				professionInfoDao.updateUserState(userId);
				//更新图片状态为审核中
				ImageInfo imageInfo = new ImageInfo();
				imageInfo.setUserId(userId);
//				imageInfo.setType("A3");
				imageInfo.setType("'A3','B3','C3'");
//				imageInfo.setlSize(3);
				//更新图片状态为审核中
				professionInfoDao.updateImageVerifyState(imageInfo);
			} else {
				//审批状态为草稿
				studentInfo.setVerifyState("A1");
			}
			//保存学生信息
			result = professionInfoDao.saveProfessionInfo(studentInfo);
		}
		//更新用户状态为学生
		SysUser sysUser = new SysUser();
		sysUser.setId(studentInfo.getUserId());
		sysUser.setType("A2");
		professionInfoDao.updateUserInfoByUserId(sysUser);
		return result;
	}
}
