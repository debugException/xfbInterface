/**
 * @(#)ImageInfoDao.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao;

import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.common.IBaseDao;

/**
 * 提升额度-上传图片Dao.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public interface ImageInfoDao extends IBaseDao{
	
	/**
	 * 
	 * 保存图片信息.
	 * @author Black
	 * @date 2015-8-14 上午11:48:17
	 *
	 * @param imageInfo
	 * @return
	 * @throws Exception
	 */
	int saveImageInfo(ImageInfo imageInfo) throws Exception;
}
