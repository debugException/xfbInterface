/**
 * @(#)PromoteAmountService.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service;

import cn.tyiti.xfb.bojo.PromoteAmount;

/**
 * 提升额度-获取数据 Service.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public interface PromoteAmountService {
	/**
	 * 
	 * 获取数据.
	 * @author Black
	 * @date 2015-8-12 下午4:55:18
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	PromoteAmount getStateInfo(Integer userId) throws Exception;

}
