package cn.tyiti.xfb.dao;

import cn.tyiti.xfb.bojo.QuotaScore;

/**
 * 额度分数表
 * */
public interface QuotaScoreDao {
	
	/**根据分数获取额度模型*/
	public QuotaScore getQuotaByScore(Integer score) throws Exception;
}
