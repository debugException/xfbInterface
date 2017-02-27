package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.XuexingAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.XuexingAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("xuexingAuthinfoDAO")
public class XuexingAuthinfoDAOImpl extends BaseDao implements XuexingAuthinfoDAO {

    public XuexingAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        XuexingAuthinfo _key = new XuexingAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_xuexing_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(XuexingAuthinfo record) {
        getSqlMapClientTemplate().insert("t_xuexing_authinfo.insert", record);
    }

    public void insertSelective(XuexingAuthinfo record) {
        getSqlMapClientTemplate().insert("t_xuexing_authinfo.insertSelective", record);
    }

    public XuexingAuthinfo selectByPrimaryKey(Integer id) {
        XuexingAuthinfo _key = new XuexingAuthinfo();
        _key.setId(id);
        XuexingAuthinfo record = (XuexingAuthinfo) getSqlMapClientTemplate().queryForObject("t_xuexing_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(XuexingAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_xuexing_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(XuexingAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_xuexing_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<XuexingAuthinfo> getXuexingAuthinfoByUserid(XuexingAuthinfo xuexingAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_xuexing_authinfo.getXuexingAuthinfoByUserid",xuexingAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void xuexingAuthinfo(XuexingAuthinfo requestXuexingAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
         getSqlMapClientTemplate().insert("t_xuexing_authinfo.insert",requestXuexingAuthinfo);

    }

    @Override
    public void updateImagePathByUserId(XuexingAuthinfo xuexingAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}