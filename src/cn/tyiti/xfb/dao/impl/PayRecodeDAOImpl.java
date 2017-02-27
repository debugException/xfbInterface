package cn.tyiti.xfb.dao.impl;

import org.springframework.stereotype.Repository;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.tyiti.xfb.bojo.PayRecode;
import cn.tyiti.xfb.dao.IPayRecodeDAO;

@Repository("payRecodeDAO")
public class PayRecodeDAOImpl extends BaseDao implements IPayRecodeDAO {
	
	/**
	 * 
	 * 名称：checkIsBillId <br/>
	 * 描述：根据账单ID校验账单是否存在 <br/>
	 * @author shenwu
	 * @date 2015年10月27日14:44:13
	 * @return
	 */
	@Override
    public BillStage checkIsBillId(BillStage bs) throws Exception {
		return (BillStage) this.getSqlMapClientTemplate().queryForObject("payRecode.CheckIsBillId",bs);
    }
	
	/**
	 * 
	 * 名称：savePayRecode <br/>
	 * 描述：保存操作流水记录 <br/>
	 * @param pr 操作流水信息 pr
	 * @author shenwu
	 * @date 2015年10月27日14:44:28
	 * @return
	 */
	public boolean savePayRecode(PayRecode pr) {
		int result = 0;
		result = (Integer) getSqlMapClientTemplate().insert("payRecode.savePayRecode", pr);
		if(result==0){
			return false;
		} else {
			return true;
		}
    }
	
	/**
	 * 
	 * 名称：updatePayState <br/>
	 * 描述：更新交易流水表状态 <br/>
	 * @param payRecode 操作流水信息 payRecode
	 * @author shenwu
	 * @date 2015年10月28日15:25:17
	 * @return
	 */
	@Override
    public void updatePayState(PayRecode payRecode) {
        getSqlMapClientTemplate().update("payRecode.updatePayState", payRecode);
    }
	
}
