package cn.tyiti.xfb.dao;

import cn.tyiti.xfb.bojo.ScoreLog;

/**
 * 得分记录表
 * */
public interface ScoreLogDao {
	/**
	 * 根据用户ID获取授信总分
	 * */
	public Integer getScoreSumById(Integer userid) throws Exception;
	
	/**
	 * 插入新的记录
	 * */
	public void inser(ScoreLog socreLog) throws Exception;
}
