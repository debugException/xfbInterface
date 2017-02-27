package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.tyiti.xfb.bojo.BankCardInfo;
import cn.tyiti.xfb.dao.BankCardInfoDao;

@Repository
public class BankCardInfoDaoImpl extends BaseDao implements BankCardInfoDao{

	@Override
	public Integer saveBankCardInfo(BankCardInfo bankCardInfo) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().insert("bankCardInfo.saveBankCardInfo",bankCardInfo);
	}

}
