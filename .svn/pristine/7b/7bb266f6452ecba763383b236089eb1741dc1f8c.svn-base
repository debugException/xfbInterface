/**
 * @(#)ProfessionInfoDaoImpl.java	1.0	2015-8-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.JobInfo;
import cn.tyiti.xfb.bojo.StudentInfo;
import cn.tyiti.xfb.dao.ProfessionInfoDao;

/**
 * 提升额度-获取职业信息/提升额度-提交职业信息Dao Impl.
 * 
 * @version 1.0 2015-8-12
 * @author Black
 */
@Repository("professionInfoDao")
public class ProfessionInfoDaoImpl extends BaseDao implements ProfessionInfoDao {

	/* （non-Javadoc）
	 * <p>Title: getProfessionInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#getProfessionInfo(java.lang.Integer)
	 */
	@Override
	public JobInfo getProfessionInfo(JobInfo jobInfo) throws Exception {
		// TODO Auto-generated method stub
		return (JobInfo) this.getSqlMapClientTemplate().queryForObject("professioninfo.getJobInfo", jobInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: insertProfessionInfo</p>
	 * <p>Description: </p>
	 * @param jobInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#insertProfessionInfo(cn.tyiti.xfb.bojo.JobInfo)
	 */
	@Override
	public int insertProfessionInfo(JobInfo jobInfo) throws Exception {
		// TODO Auto-generated method stub
		//职业信息插入
		//if(WcfCallUtils.callSetMemberInfo(, userType))
		Object obj = getSqlMapClientTemplate().insert("professioninfo.insertJobInfo", jobInfo);
		return 0;
	}

	/* （non-Javadoc）
	 * <p>Title: saveProfessionInfo</p>
	 * <p>Description: </p>
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#saveProfessionInfo(cn.tyiti.xfb.bojo.ProfessionInfo)
	 */
	@Override
	public int saveProfessionInfo(JobInfo jobInfo) throws Exception {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("professioninfo.saveJobInfo", jobInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: insertProfessionInfo</p>
	 * <p>Description: </p>
	 * @param studentInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#insertProfessionInfo(cn.tyiti.xfb.bojo.StudentInfo)
	 */
	@Override
	public int insertProfessionInfo(StudentInfo studentInfo) throws Exception {
		// TODO Auto-generated method stub
		Object obj = getSqlMapClientTemplate().insert("professioninfo.insertStudentInfo", studentInfo);
		return 0;
	}

	/* （non-Javadoc）
	 * <p>Title: getProfessionInfo</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#getProfessionInfo(java.lang.Integer)
	 */
	@Override
	public StudentInfo getProfessionInfo(StudentInfo studentInfo) throws Exception {
		// TODO Auto-generated method stub
		return (StudentInfo) this.getSqlMapClientTemplate().queryForObject("professioninfo.getStudentInfo", studentInfo);
	}

	/* （non-Javadoc）
	 * <p>Title: saveProfessionInfo</p>
	 * <p>Description: </p>
	 * @param ProfessionInfo
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.ProfessionInfoDao#saveProfessionInfo(cn.tyiti.xfb.bojo.ProfessionInfo)
	 */
	@Override
	public int saveProfessionInfo(StudentInfo studentInfo) throws Exception {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("professioninfo.saveStudentInfo", studentInfo);
	}
	
}
