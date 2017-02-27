package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.Customer;

/**
 * .
 * <p>
 * Copyright: Copyright (c) 2014-10-23
 * <p>
 * Company: 北京幻方朗睿技术有限公司
 * <p>
 * Author: 龙欢
 * <p>
 * Version: 1.0
 * <p>
 */
public interface CustomerDao
{

    /**
     * 登陆
     * 
     * @param loginName
     * @param loginPassword
     * @return
     * @throws Exception
     */
    public Customer login(String loginName, String loginPassword)
            throws Exception;

    /**
     * 根据类型，ID查询
     * 
     * @param openType
     *            开放平台类型
     * @param openID
     *            开发平台ID
     * @return
     * @throws Exception
     */
    public Customer queryCustomerByIDType(int openType, String openID)
            throws Exception;

    /**
     * 插入一条数据
     * 
     * @param customer
     * @return
     * @throws Exception
     */
    public Long insertCustomer(Customer customer) throws Exception;

    /**
     * 根据openType,openID,ID更新表open_nick,last_login_time
     * 
     * @param customer
     * @throws Exception
     */
    public void updateCustomer(Customer customer) throws Exception;
}
