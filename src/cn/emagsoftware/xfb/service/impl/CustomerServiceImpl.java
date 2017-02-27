package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.frame.service.BaseService;
import cn.emagsoftware.xfb.dao.CustomerDao;
import cn.emagsoftware.xfb.pojo.Customer;
import cn.emagsoftware.xfb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public String registration(String userName, String userPhone, String userPwd)
            throws Exception {
        // TODO 1.验证登录名唯一性
        // 2.密码MD5加密（客户端已加密，直接存储）
        return ("111111");
    }
    @Override
    public String getVerifCode(String userPhone, String idNumber)
            throws Exception {
        //todo
        return ("2222222222");
    }

    @Override
    public Customer login(int openType, String openID, String openNick)
            throws Exception {
        Date currentDate = new Date();
        // 1.根据openType,openID查询
        Customer customer = customerDao.queryCustomerByIDType(openType, openID);
        // 2.查询不出数据，则插入一条，然后返回
        if (null == customer) {
            customer = new Customer();
            customer.setOpenId(openID);
            customer.setOpenNick(openNick);
            customer.setOpenType(openType);
            customer.setLastLoginTime(currentDate);
            customer.setRegiestTime(currentDate);
            Long customerID = customerDao.insertCustomer(customer);
            customer.setCustomerId(customerID);
        }
        // 3.查询出数据，更新数据库 open_nick,last_login_time
        else {
            customer.setOpenNick(openNick);
            customer.setLastLoginTime(currentDate);
            customerDao.updateCustomer(customer);
        }
        return customer;
    }

}