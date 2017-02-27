package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.EmailAuthinfo;

import java.util.List;


public interface EmailAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(EmailAuthinfo record);

    void insertSelective(EmailAuthinfo record);

    EmailAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmailAuthinfo record);

    int updateByPrimaryKey(EmailAuthinfo record);


    List<EmailAuthinfo> getEmailAuthinfoByUserid(EmailAuthinfo  emailAuthinfo);

    void  emailAuthinfo(EmailAuthinfo requestEmailAuthinfo);

    void updateImagePathByUserId(EmailAuthinfo  emailAuthinfo);

    void updateUserEmail(EmailAuthinfo emailAuthinfo);

}