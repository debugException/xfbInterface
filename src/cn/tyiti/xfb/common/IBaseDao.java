/**
 * @(#)BaseDao.java	1.0	2015-8-17
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.common;

import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;


/**
 * 公共Dao接口类.
 * 
 * @version 1.0 2015-8-17
 * @author Black
 */
public interface IBaseDao{
	//用户表中verify_state设置为A1(新进)；has_verify设置为A1(需要)
	int updateUserState(Integer userId) throws Exception;
	//修改用户信息
	//用户表中has_verify设置为A1(需要) 会员类型：A1:上班族 A2:学生
	int updateUserInfoByUserId(SysUser sysUser) throws Exception;
	//更新用户图片草稿为审核中
	int updateImageVerifyState(ImageInfo imageInfo) throws Exception;
}
