package cn.tyiti.xfb.dao;

import cn.emagsoftware.xfb.pojo.BillStage;
import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.tyiti.xfb.bojo.PayRecode;
import cn.tyiti.xfb.common.IBaseDao;

/**
 * 
 * 类名称：IPayRecodeDAO <br/>
 * 类描述：支付流水信息<br/>
 * 创建时间：2015年10月27日14:22:28 <br/>
 * @author shenwu
 * @version V1.0
 */
public interface IPayRecodeDAO extends IBaseDao {

	/**
	 * 根据账单ID校验账单是否存在
	 * @author shenwu
	 * @return BillStage 账单信息
	 */
	BillStage checkIsBillId(BillStage bs) throws Exception;
	
	/**
	 * 保存操作流水记录
	 * @param pr
	 * @author shenwu
	 * @return	true或false	true:保存成功		false:保存失败
	 */
	boolean savePayRecode(PayRecode pr) throws Exception;
	
	/**
	 * 更新交易流水表状态
	 * @param payRecode
	 * @author shenwu
	 * @throws Exception
	 */
	void updatePayState(PayRecode payRecode) throws Exception;
}
