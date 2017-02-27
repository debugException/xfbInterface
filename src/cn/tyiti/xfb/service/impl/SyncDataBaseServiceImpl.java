/**
 * @(#)SyncDataBaseServiceImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emagsoftware.utils.WcfCallUtils;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.dao.SyncDataBaseDao;
import cn.tyiti.xfb.service.SyncDataBaseService;
import cn.tyiti.xfb.utils.LogUtil;

/**
 * 同步用户数据到市场版Service Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Service
public class SyncDataBaseServiceImpl implements SyncDataBaseService {

	@Autowired
	private SyncDataBaseDao syncDataBaseDao;
    @Autowired
    private WcfCallUtils wcfCallUtils;

	/* （non-Javadoc）
	 * <p>Title: getUserInfo</p>
	 * <p>Description: </p>
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.SyncDataBaseService#getUserInfo()
	 */
	@Override
	public void toProcessData() throws Exception {
		// TODO Auto-generated method stub
		List<SysUser> userList = syncDataBaseDao.getUserInfo();
		String preStr = "SyncDataBaseServiceImpl.toProcessData";
		LogUtil.info(preStr+"--------数据迁移开始--------");
		LogUtil.info(preStr+"--------会员总条数--------"+userList.size());
		for(SysUser user : userList){
			String loginName = user.getLoginName();
			//用户状态 0-有效 1-失效
			int state = user.getStatus();
//			会员类型：A1:上班族 A2:学生
			String type = user.getType();
			Integer userId = user.getId();
			LogUtil.info(preStr+"--------param--------"+"loginName:"+loginName+"   password:"+user.getPassWord()
					+"   realName:"+user.getRealName()+"   state:"+state+"   type:"+type);
			//需要增加身份证号码传过去
			boolean result = wcfCallUtils.callRegister(userId, loginName, user.getPassWord(), user.getRealName(), user.getDescription());
			if(result){
				if(state == 1) {
					wcfCallUtils.callSetUserStatus(userId, loginName, String.valueOf(state));
				}
				
				if("A1".equals(type)){
					wcfCallUtils.callSetMemberType(userId, loginName, -5);
				} else if ("A2".equals(type)){
					wcfCallUtils.callSetMemberType(userId, loginName, -1);
				} else {
					//不处理
				}
			} else {
				LogUtil.error(preStr+"--------写入市场版失败--------");
				//throw new Exception(preStr+"--------写入市场版失败--------");
			}
		}
		LogUtil.info(preStr+"--------数据迁移结束--------");
	}
	

}
