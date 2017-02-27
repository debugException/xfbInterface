package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.dao.CreditModelDao;

/**
 * 信用模型
 * 
 * @version 1.0 2015-9-12
 * @author KELLEN
 */
@Repository("creditModelDao")
public class CreditModelDaoImpl extends BaseDao implements CreditModelDao {

	@Override
	public Integer getScoreByCode(String code) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("creditmodel.getScoreByCode",code);
	}

}
