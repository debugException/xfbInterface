/**
 * @(#)ImageInfoDaoImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.dao.ImageInfoDao;

/**
 * 提升额度-上传图片Dao Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Repository("imageInfoDao")
public class ImageInfoDaoImpl extends BaseDao implements ImageInfoDao {

	/* （non-Javadoc）
	 * <p>Title: saveImageInfo</p>
	 * <p>Description: </p>
	 * @param imageInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ImageInfoDao#saveImageInfo(cn.tyiti.xfb.bojo.ImageInfo)
	 */
	@Override
	public int saveImageInfo(ImageInfo imageInfo) throws Exception {
		// TODO Auto-generated method stub
		Object obj = getSqlMapClientTemplate().insert("imageinfo.insertImageinfo", imageInfo);
		return 0;
	}
	
}
