package cn.tyiti.xfb.dao.blacklist.impl;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.blacklist.BlistInfo;
import cn.tyiti.xfb.dao.blacklist.IblistInfoManageDao;
import cn.tyiti.xfb.dao.blacklist.IqlblackInfoManageDao;

/** 
 * 创建时间：2015-11-24 下午9:36:51  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：BlistInfoManageDao.java  
 * 类说明：  
 */
@Repository("blistInfoManageDao")
public class BlistInfoManageDao extends BaseDao implements IblistInfoManageDao {

	@Autowired
	private IqlblackInfoManageDao qlblackInfoManageDao ;

	@Override
	public void insert(BlistInfo blistInfo) throws Exception {
		this.getSqlMapClientTemplate().insert("t_bList_message.insert_bList",blistInfo);
		
	}

}
