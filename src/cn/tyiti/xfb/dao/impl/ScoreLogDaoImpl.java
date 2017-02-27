package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;
import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.ScoreLog;
import cn.tyiti.xfb.dao.ScoreLogDao;

@Repository("scoreLogDao")
public class ScoreLogDaoImpl extends BaseDao implements ScoreLogDao {

	@Override
	public Integer getScoreSumById(Integer id) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("scorelog.getScoreSumById",id);
	}

	@Override
	public void inser(ScoreLog socreLog) throws Exception{
		getSqlMapClientTemplate().insert("scorelog.insert",socreLog);
	}

}
