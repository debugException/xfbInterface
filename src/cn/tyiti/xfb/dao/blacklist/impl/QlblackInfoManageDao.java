package cn.tyiti.xfb.dao.blacklist.impl;   

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.blacklist.QlblackInfo;
import cn.tyiti.xfb.dao.blacklist.IqlblackInfoManageDao;

/** 
 * 创建时间：2015-11-24 下午9:37:08  
 * 项目名称：xfbManage  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：QlblackInfoManageDao.java  
 * 类说明：  
 */
@Repository("qlblackInfoManageDao")
public class QlblackInfoManageDao extends BaseDao implements IqlblackInfoManageDao {
	
	@Override
	public void insert(QlblackInfo qlblackInfo) throws Exception {
		this.getSqlMapClientTemplate().insert("t_qlblackinfo_result.insert_qlblackinfo",qlblackInfo);
	}

}
 