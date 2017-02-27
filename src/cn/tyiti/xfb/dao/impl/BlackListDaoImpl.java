package cn.tyiti.xfb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.UserInfo;
import cn.tyiti.xfb.dao.BlackListDao;

@Repository
public class BlackListDaoImpl extends BaseDao implements BlackListDao {

	@Override
	public UserInfo getUserInfoByUserId(Integer userId) throws Exception {
		return (UserInfo) this.getSqlMapClientTemplate().queryForObject("t_blackList_main.getUserInfoByUserId",userId);
	}

	@Override
	public void addData(UserInfo userInfo) throws Exception {
		this.getSqlMapClientTemplate().insert("t_blackList_main.addData",userInfo);
	}

	@Override
	public List<UserInfo> getUserInfo() throws Exception {
		return this.getSqlMapClientTemplate().queryForList("t_blackList_main.getUserInfo");
//		return this.getSqlMapClientTemplate().queryForList("t_blackList_main.getUserInfoTemp");
	}

	@Override
	public void batchInsert(List<UserInfo> list) throws Exception {
		this.getSqlMapClientTemplate().insert("t_blackList_main.batchInsert",list);
	}

}
