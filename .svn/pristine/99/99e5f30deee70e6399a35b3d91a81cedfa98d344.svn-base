package cn.tyiti.xfb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.QuotaScore;
import cn.tyiti.xfb.dao.QuotaScoreDao;

@Repository("quotaScoreDao")
public class QuotaScoreDaoImpl extends BaseDao implements QuotaScoreDao {

	@Override
	public QuotaScore getQuotaByScore(Integer score) throws Exception{
		List<QuotaScore> list =  getSqlMapClientTemplate().queryForList("quotascore.getScoreByCode",score);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

}
