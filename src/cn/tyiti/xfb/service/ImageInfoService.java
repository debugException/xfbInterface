/**
 * @(#)ImageInfoService.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service;

import cn.tyiti.xfb.bojo.ImageInfo;

/**
 * 提升额度-上传图片Service.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public interface ImageInfoService {

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
	
	/**
	 * 
	 * 提交图片信息.
	 * @author Black
	 * @date 2015-8-28 下午3:14:18
	 *
	 * @param imageInfo
	 * @return
	 * @throws Exception
	 */
	int commitImageInfo(ImageInfo imageInfo) throws Exception;
}
