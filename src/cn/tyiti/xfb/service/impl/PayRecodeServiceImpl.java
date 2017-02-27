package cn.tyiti.xfb.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emagsoftware.xfb.pojo.BillStage;
import cn.tyiti.xfb.bojo.PayRecode;
import cn.tyiti.xfb.dao.IPayRecodeDAO;
import cn.tyiti.xfb.service.IPayRecodeService;

/**
 * 
 * 类名称：PayRecodeServiceImpl <br/>
 * 类描述：支付流水信息服务层实现类 <br/>
 * 创建时间：2015年10月27日14:12:02 <br/>
 * @author shenwu
 * @version V1.0
 */
@Service("payRecodeService")
public class PayRecodeServiceImpl implements IPayRecodeService {

	@Autowired
    private IPayRecodeDAO payRecodeDAO;
	
	/**
	 * 
	 * 名称：checkIsBillId <br/>
	 * 描述：根据账单ID校验账单是否存在 <br/>
	 * @author shenwu
	 * @date 2015年10月27日14:11:15
	 * @return
	 */
	@Override
	public BillStage checkIsBillId(BillStage bs) throws Exception {
		return payRecodeDAO.checkIsBillId(bs);
	}
	
	/**
	 * 
	 * 名称：savePayRecode <br/>
	 * 描述：保存操作流水记录 <br/>
	 * @author shenwu
	 * @date 2015年10月27日14:16:45
	 * @return
	 * @throws Exception 
	 */
	@Override
	public PayRecode savePayRecode(PayRecode pr, BillStage bs) throws Exception {
		String str = bs.getBillNo();
		String tradeNo = "";
		if(str.length()>15){
			tradeNo = str.substring(0,15);
		}else {
			tradeNo = str;
		}
		pr.setBillId(Integer.valueOf(bs.getId()));			//账单ID
		pr.setTradeNo(tradeNo+new Date().getTime()/1000);	//支付流水号
		pr.setPayAmount(pr.getPayAmount());					//支付金额
		pr.setPayState("A1");								//支付状态		A1.未支付（默认）	A2.已支付
		pr.setPayType(pr.getPayType());						//支付类型		A1.支付宝	A2.微信
		PayRecode _pr = new PayRecode();
		boolean b;
		b = payRecodeDAO.savePayRecode(pr);
		if(b == true){
			_pr.setTradeNo(pr.getTradeNo());
			return _pr;
		}else {
			_pr.setTradeNo(pr.getTradeNo());
			return _pr;
		}
	}
	
	/**
	 * 
	 * 名称：updatePayState <br/>
	 * 描述：更新交易流水表状态 <br/>
	 * @author shenwu
	 * @date 2015年10月28日15:23:06
	 * @return
	 * @throws Exception 
	 */
	@Override
    public void updatePayState(PayRecode payRecode) throws Exception{
		payRecodeDAO.updatePayState(payRecode);
    }
	
}
