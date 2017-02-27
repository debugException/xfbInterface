package cn.emagsoftware.xfb.service;

import java.util.Map;

import cn.emagsoftware.xfb.dto.OrderInfoReq;
import cn.emagsoftware.xfb.pojo.OrderInfo;

public interface OrderService {
    /**
     * 根据userID,查找该ID下的订单
     *
     * @param userId 用户ID
     * @param startNumber
     * @param pageSize
     * @return videoList:订单列表List(Order)，orderTotal:总数Integer
     * @throws Exception
     */
    public Map<String, Object> orderListByUserid(int userId,String flag, long startNumber, long pageSize) throws Exception;
    
    void createOrder(OrderInfo order) throws Exception;

    boolean checkOrderInfo(OrderInfoReq orderStageReq)throws Exception;

    OrderInfo getOrderInfoById(OrderInfo orderInfo)throws Exception;

    void orderCancellation(OrderInfo orderInfo)throws Exception;
    
    void orderStage(OrderInfo orderInfo)throws Exception;
    
	public Map<String, Object> findOrderInfoByOwnerUserid(OrderInfo orderInfo,
			int startNum, int pageSize) throws Exception;
	/**
	 * 根据订单id修改订单状态
	 * @param paramMap 订单id 状态
	 */
	public void updateOrderStatusByOrderId(Map paramMap);
	
	/**
	 * 
	 * 查询需要确认订单的数量.
	 * @author Black
	 * @date 2015-9-15 下午2:17:11
	 *
	 * @param userId 用户Id
	 * @return
	 */
	int selectByUserIdCount(Integer userId);
}
