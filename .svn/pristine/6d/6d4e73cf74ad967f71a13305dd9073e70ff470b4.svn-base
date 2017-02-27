package cn.emagsoftware.xfb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.CreditDao;
import cn.emagsoftware.xfb.pojo.CollectInfo;
import cn.emagsoftware.xfb.pojo.CreditInfo;
import cn.emagsoftware.xfb.pojo.LoanInfo;

@Repository("creditDao")
public class CreditDaoImpl extends BaseDao implements CreditDao {
	
	public CreditDaoImpl(){
		super();
	}

	//查询个人征信
	@Override
	public void addCreditInfo(CreditInfo creditInfo) throws Exception {
		getSqlMapClientTemplate().insert("t_credit_creditInfo.insert", creditInfo);
	}

	//保存查询结果集
	@Override
	public void saveLoanInfo(LoanInfo loanInfo) throws Exception {
		getSqlMapClientTemplate().insert("t_credit_creditInfo.addLoanInfo", loanInfo);		
	}

	//查询用户id
	@Override
	public String getUserIdByTrxNo(String trxNo) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("t_credit_creditInfo.getUserIdByTrxNo", trxNo);
	}
	//查询用户
	@Override
	public String getCardNumberByTrxNo(String trxNo) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("t_credit_creditInfo.getCardNumberByTrxNo", trxNo);
	}

	//修改黑名单表标识位trxNo
	@Override
	public int updateTrxNo(Map<String, String> map) throws Exception {
		return getSqlMapClientTemplate().update("t_credit_creditInfo.updateTrxNo", map);
	}

	@Override
	public List<CollectInfo> queryLoanInfos(Map<String, String> map) throws Exception {
		return  getSqlMapClientTemplate().queryForList("t_credit_creditInfo.queryLoanInfos", map);
	}

}
