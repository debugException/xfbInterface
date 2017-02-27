package cn.emagsoftware.xfb.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.emagsoftware.frame.service.BaseService;
import cn.emagsoftware.xfb.dao.OrderInfoDAO;
import cn.emagsoftware.xfb.dto.OrderInfoReq;
import cn.emagsoftware.xfb.pojo.OrderInfo;
import cn.emagsoftware.xfb.service.OrderService;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {

    @Autowired
    private OrderInfoDAO orderInfoDAO;

    /**
     * 根据userID,查找该ID下的订单
     *
     * @param userId      用户ID
     * @param startNumber
     * @param pageSize
     * @return videoList:订单列表List(Order)，orderTotal:总数Integer
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> orderListByUserid(int userId,String flag, long startNumber, long pageSize) throws Exception {
        Map<String, Object> map = orderInfoDAO.orderListByUserid(userId,flag,startNumber, pageSize);
        return map;
    }
    
    @Override
    public void createOrder(OrderInfo order) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        orderInfoDAO.insert(order);
    }

    @Override
    public boolean checkOrderInfo(OrderInfoReq orderStageReq) throws Exception {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OrderInfo getOrderInfoById(OrderInfo orderInfo) throws Exception {
        return orderInfoDAO.selectByPrimaryKey(orderInfo.getId());  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void orderCancellation(OrderInfo orderInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        orderInfoDAO.orderCancellation(orderInfo);
    }
    
    /**
     * 订单确认分期后修改订单状态，分期数以及服务费
     * */
    @Override
    public void orderStage(OrderInfo orderInfo) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        orderInfoDAO.orderStage(orderInfo);
    }
	@Override
	public Map<String, Object> findOrderInfoByOwnerUserid(OrderInfo orderInfo,
			int startNum, int pageSize) throws Exception {
		
		return orderInfoDAO.findOrderInfoByOwnerUserid(orderInfo, startNum, pageSize);
	}
	@Override
	public void updateOrderStatusByOrderId(Map paramMap) {
		orderInfoDAO.updateOrderStatusByOrderId(paramMap);
	}
	/* （non-Javadoc）
	 * <p>Title: selectByUserIdCount</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @see cn.emagsoftware.xfb.service.OrderService#selectByUserIdCount(java.lang.Integer)
	 */
	@Override
	public int selectByUserIdCount(Integer userId) {
		// TODO Auto-generated method stub
		return orderInfoDAO.selectByUserIdCount(userId);
	}
}
