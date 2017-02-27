package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.EmailAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.EmailAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emailAuthinfoDAO")
public class EmailAuthinfoDAOImpl extends BaseDao implements EmailAuthinfoDAO {

    public EmailAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        EmailAuthinfo _key = new EmailAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_email_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(EmailAuthinfo record) {
        getSqlMapClientTemplate().insert("t_email_authinfo.insert", record);
    }

    public void insertSelective(EmailAuthinfo record) {
        getSqlMapClientTemplate().insert("t_email_authinfo.insertSelective", record);
    }

    public EmailAuthinfo selectByPrimaryKey(Integer id) {
        EmailAuthinfo _key = new EmailAuthinfo();
        _key.setId(id);
        EmailAuthinfo record = (EmailAuthinfo) getSqlMapClientTemplate().queryForObject("t_email_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(EmailAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_email_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(EmailAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_email_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<EmailAuthinfo> getEmailAuthinfoByUserid(EmailAuthinfo emailAuthinfo) {
        return   getSqlMapClientTemplate().queryForList("t_email_authinfo.getEmailAuthinfoByUserid",emailAuthinfo);
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void emailAuthinfo(EmailAuthinfo requestEmailAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().insert("t_email_authinfo.insert", requestEmailAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(EmailAuthinfo emailAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void updateUserEmail(EmailAuthinfo emailAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.

        getSqlMapClientTemplate().update("t_email_authinfo.updateUserEmail", emailAuthinfo);
    }
}