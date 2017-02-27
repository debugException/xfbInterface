package cn.tyiti.xfb.dao.jpush.impl;   

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.JpushRecord;
import cn.tyiti.xfb.dao.jpush.IjpushInfoDao;

/** 
 * 创建时间：2015-12-9 上午11:00:25  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：JpushInfoDao.java  
 * 类说明：  
 */
@Repository("jpushInfoDao")
public class JpushInfoDao extends BaseDao implements IjpushInfoDao{

	@Override
	public int insertJpushInfo(JpushRecord jpushRecord) {
		this.getSqlMapClientTemplate().insert("t_jpush_record.insert_jpushrecord",jpushRecord);
		return 0;
	}

}
 