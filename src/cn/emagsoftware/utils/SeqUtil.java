package cn.emagsoftware.utils;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;

/**
 * @Title: 序列处理工具类
 */
@Repository("seqUtil")
public class SeqUtil extends BaseDao
{
	/**
	 * 根据序列名称获取序列下个值
	 * 
	 * @param seq
	 *            序列名称
	 * @param type
	 *            数据类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getNextValueBySeqName(String seq)
	{
		Object id = getSqlMapClientTemplate().queryForObject("sql_mysql_seq.getNextval", seq);

		return (T)id;
	}
}
