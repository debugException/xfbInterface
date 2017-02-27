/**
 * @(#)InterfaceLogDaoImpl.java	1.0	2015-9-12
 * Copyright 2014 [天尧], Inc. All rights reserved.
 * Website: http://www.tyiti.com/
 */
package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.pojo.SysUser;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.bojo.InterfaceLog;
import cn.tyiti.xfb.dao.InterfaceLogDao;
import cn.tyiti.xfb.utils.LogUtil;

/**
 * 接口异常记录Dao Impl.
 * 
 * @version 1.0 2015-9-12
 * @author Black
 */
@Repository("interfaceLogDao")
public class InterfaceLogDaoImpl extends BaseDao implements InterfaceLogDao {

	/* （non-Javadoc）
	 * <p>Title: insertInterfacelog</p>
	 * <p>Description: </p>
	 * @param interfaceLog
	 * @return
	 * @throws Exception
	 * @see cn.tyiti.xfb.dao.InterfaceLogDao#insertInterfacelog(cn.tyiti.xfb.bojo.InterfaceLog)
	 */
	@Override
	public void insertInterfacelog(Integer tableId, String tableName) {
		InterfaceLog interfaceLog = new InterfaceLog();
		interfaceLog.setTableId(tableId);
		interfaceLog.setTableName(tableName);
		/*状态
        0：未处理
        1：已处理*/
		short state = 0;
		interfaceLog.setState(state);
		/*
		 * 由于保证微商城不影响V1.1版本的正常运行，就算这出错也不处理， 记录日志即可
		 * author：Black
		 * date:2015-09-12
		 */
		try {
			this.getSqlMapClientTemplate().insert("interfacelog.insertInterfacelog", interfaceLog);
		} catch (Exception ex){
			LogUtil.error("InterfaceLogDaoImpl.insertInterfacelog param{tableId:"+tableId+";tableName:"+tableName+"}"+ex);
		}
	}

}
