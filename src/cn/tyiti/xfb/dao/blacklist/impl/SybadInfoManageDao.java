package cn.tyiti.xfb.dao.blacklist.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.blacklist.SybadInfo;
import cn.tyiti.xfb.dao.blacklist.IsybadInfoManageDao;

/**
 * 创建时间：2015-11-24 下午9:37:23 项目名称：xfbManage
 * 
 * @author liminghua
 * @version 1.0
 * @since JDK 1.6 文件名称：SybadInfoManageDao.java 类说明：
 */
@Repository("sybadInfoManageDao")
public class SybadInfoManageDao extends BaseDao implements IsybadInfoManageDao {

	@Override
	public void insert(SybadInfo sybadInfo) throws Exception {
		this.getSqlMapClientTemplate().insert("t_sybadinfo_result.insert_sysbadinfo",sybadInfo);
		
	}
}
