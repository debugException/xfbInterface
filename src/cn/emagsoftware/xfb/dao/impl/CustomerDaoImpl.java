package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.frame.exception.BaseException;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.xfb.dao.CustomerDao;
import cn.emagsoftware.xfb.pojo.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDao implements CustomerDao
{

    @Override
    public Customer login(String loginName, String loginPassword)
            throws Exception
    {
        try
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("loginName", loginName);
            map.put("loginPassword", loginPassword);
            Customer customer = (Customer) this.getSqlMapClientTemplate()
                    .queryForObject("customer.login", map);
            return customer;
        }
        catch (Exception e)
        {
            logger.error("CustomerDaoImpl.login", e);
            throw new BaseException(Constant.ERROR_CODE_9995,
                    Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
        }
    }

    @Override
    public Customer queryCustomerByIDType(int openType, String openID)
            throws Exception
    {
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("openType", openType);
            map.put("openID", openID);
            Customer customer = (Customer) this.getSqlMapClientTemplate()
                    .queryForObject("customer.queryCustomerByIDType", map);
            return customer;
        }
        catch (Exception e)
        {
            logger.error("CustomerDaoImpl.queryCustomerByIDType", e);
            throw new BaseException(Constant.ERROR_CODE_9995,
                    Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
        }
    }

    @Override
    public Long insertCustomer(Customer customer) throws Exception
    {
        try
        {
            Long customerID = (Long) this.getSqlMapClientTemplate().insert(
                    "customer.insertCustomer", customer);
            return customerID;
        }
        catch (Exception e)
        {
            logger.error("CustomerDaoImpl.insertCustomer", e);
            throw new BaseException(Constant.ERROR_CODE_9995,
                    Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception
    {
        try
        {
            this.getSqlMapClientTemplate().update("customer.updateCustomer",
                    customer);
        }
        catch (Exception e)
        {
            logger.error("CustomerDaoImpl.updateCustomer", e);
            throw new BaseException(Constant.ERROR_CODE_9995,
                    Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9995));
        }

    }

}
