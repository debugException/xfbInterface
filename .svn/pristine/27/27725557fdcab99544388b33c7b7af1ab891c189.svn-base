package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.xfb.dao.BillStageDAO;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.emagsoftware.xfb.service.BillStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yindongyong
 * Date: 15-4-7
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BillStageServiceImpl implements BillStageService {

    @Autowired
    private BillStageDAO  billStageDAO;


    @Override
    public BillStage getBillById(BillStage bill) throws Exception {
        return billStageDAO.selectByPrimaryKey(bill.getId());  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int batchCreateBill(List<BillStage> list) throws Exception {
        return billStageDAO.batchCreateBill(list);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> billList(BillStage billStage, long startNumber, long pageSize) throws Exception {

        return billStageDAO.billList( billStage,  startNumber,  pageSize);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 账单还款后更新账单状态和还款额
     * */
    @Override
    public void updateBill(BillStage billStage) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        billStageDAO.updateStatusAndAmount(billStage);
    }

    @Override
    public void updateBillByOrder(BillStage billStage) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        billStageDAO.updateBillByOrder(billStage);
    }


    @Override
    public BillStage getBillByOrder(BillStage bill) throws Exception {
        List<BillStage> list = billStageDAO.getBillByOrder(bill);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public List<BillStage> getBillByOrderId(Integer orderId) {
		return billStageDAO.getBillByOrderId(orderId);
	}
	
	/**
	 * 获取账单详情
	 */
	@Override
	public BillStage getBillDetail(Integer billId) {
		return billStageDAO.getBillDetail(billId);
	}

	@Override
	public int getBillFlagByOrderId(Integer orderId) {
		return billStageDAO.getBillFlagByOrderId(orderId);
	}
	
	/**
	 * 
	 * 名称：selTotalAllByOrderNo <br/>
	 * 描述：根据流水号获取总金额(流水表) <br/>
	 * @author shenwu
	 * @date 2015年10月28日14:35:19
	 * @return
	 */
	@Override
	public BillStage selTotalAllByOrderNo(String orderNo) throws Exception{
		return billStageDAO.selTotalAllByOrderNo(orderNo);
	}
	
	/**
	 * 
	 * 名称：selTotalAllByOrderNo <br/>
	 * 描述：根据流水号获取总金额（账单表） <br/>
	 * @author shenwu
	 * @date 2015年11月6日10:51:56
	 * @return
	 */
	@Override
	public BillStage selTotalAllByBillNo(String billNo) throws Exception{
		return billStageDAO.selTotalAllByBillNo(billNo);
	}
	/**
	 * 查询七日待还帐单数量
	 */
	@Override
	public int gettSevenBillNumByOwnerUserId(Integer orderId) {
		
		return billStageDAO.gettSevenBillNumByOwnerUserId(orderId);
	}
	
}
