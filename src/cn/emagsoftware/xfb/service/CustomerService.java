package cn.emagsoftware.xfb.service;

import cn.emagsoftware.xfb.pojo.Customer;

public interface CustomerService {
    /**
     * 注册
     *
     * @param userName
     * @param userPhone
     * @param userPwd
     * @return
     * @throws Exception
     */
    public String registration(String userName, String userPhone, String userPwd)
            throws Exception;

    public String getVerifCode(String userPhone, String idNumber)
            throws Exception;

    /**
     * 登陆，登陆成功返回用户信息
     *
     * @param openType 开放平台类型
     * @param openID   开发平台ID
     * @param openNick 开发平台昵称
     * @return
     * @throws Exception
     */
    public Customer login(int openType, String openID, String openNick)
            throws Exception;
}
