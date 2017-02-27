package cn.emagsoftware.xfb.dao;

import java.util.Map;

import cn.emagsoftware.xfb.pojo.OrderInfo;

public interface OrderInfoDAO { 
    int deleteByPrimaryKey(int id);

    void insert(OrderInfo record);

    void insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(int id);

    public Map<String, Object> orderListByUserid(int userId, String flag,long startNumber, long pageSize) throws Exception;
    
    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    void orderCancellation(OrderInfo orderInfo);
    
    void orderStage(OrderInfo orderInfo);

	Map<String, Object> findOrderInfoByOwnerUserid(OrderInfo orderInfo,
			int startNum, int pageSize) throws Exception;
	/**
	 * 根据订单id修改订单状态
	 * @param paramMap
	 */
	void updateOrderStatusByOrderId(Map paramMap);
	
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