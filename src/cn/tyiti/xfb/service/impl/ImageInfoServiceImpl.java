/**
 * @(#)ImageInfoServiceImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.dao.ImageInfoDao;
import cn.tyiti.xfb.service.ImageInfoService;

/**
 * 提升额度-上传图片Service Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Service
public class ImageInfoServiceImpl implements ImageInfoService {

	@Autowired
	private ImageInfoDao imageInfoDao;

	/* （non-Javadoc）
	 * <p>Title: saveImageInfo</p>
	 * <p>Description: </p>
	 * @param imageInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.ImageInfoService#saveImageInfo(cn.tyiti.xfb.bojo.ImageInfo)
	 */ 
	@Override
	public int saveImageInfo(ImageInfo imageInfo) throws Exception {
		return imageInfoDao.saveImageInfo(imageInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: commitImageInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.service.ImageInfoService#commitImageInfo(java.lang.Integer)
	 */
	@Override
	public int commitImageInfo(ImageInfo imageInfo) throws Exception {
		// TODO Auto-generated method stub//更新图片状态为审核中
		//更新图片状态为审核中
		imageInfoDao.updateImageVerifyState(imageInfo);
		SysUser sysUser = new SysUser();
		sysUser.setId(imageInfo.getUserId());
		sysUser.setHasVerify("A1");
		return imageInfoDao.updateUserInfoByUserId(sysUser);
	}

}
