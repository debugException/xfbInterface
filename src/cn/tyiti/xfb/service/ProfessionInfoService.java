/**
 * @(#)ProfessionInfoService.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.service;

import cn.tyiti.xfb.bojo.JobInfo;
import cn.tyiti.xfb.bojo.StudentInfo;

/**
 * 提升额度-获取职业信息/提升额度-提交职业信息 Service.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
public interface ProfessionInfoService {
	/**
	 * 
	 * 获取职业信息.
	 * @author Black
	 * @date 2015-8-12 下午4:55:18
	 *
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 */
	JobInfo getProfessionInfo(JobInfo jobInfo) throws Exception;
	
	/**
	 * 
	 * 提交职业信息.
	 * @author Black
	 * @date 2015-8-14 上午11:48:17
	 *
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 */
	int saveProfessionInfo(JobInfo jobInfo) throws Exception;
	
	/**
	 * 
	 * 获取学生信息.
	 * @author Black
	 * @date 2015-8-12 下午4:55:18
	 *
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 */
	StudentInfo getProfessionInfo(StudentInfo studentInfo) throws Exception;
	
	/**
	 * 
	 * 提交学生信息.
	 * @author Black
	 * @date 2015-8-14 上午11:48:17
	 *
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 */
	int saveProfessionInfo(StudentInfo studentInfo) throws Exception;
}
