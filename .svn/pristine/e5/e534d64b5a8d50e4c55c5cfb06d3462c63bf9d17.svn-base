package cn.tyiti.xfb.service;

import cn.emagsoftware.xfb.pojo.BillStage;
import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.tyiti.xfb.bojo.PayRecode;

/**
 * 
 * 类名称：IPayRecodeService <br/>
 * 类描述：接口支付流水接口服务层  <br/>
 * @date 2015年10月27日13:48:06 <br/>
 * @author SHENWU
 * @version V1.0
 */
public interface IPayRecodeService {

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
	 */
	PayRecode savePayRecode(PayRecode pr, BillStage bs) throws Exception;

	/**
	 * 更新交易流水表状态
	 * @param payRecode
	 * @author shenwu
	 * @throws Exception
	 */
	void updatePayState(PayRecode payRecode) throws Exception;
}
