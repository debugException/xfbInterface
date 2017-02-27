package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.SinaAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("sinaAuthinfoDAO")
public class SinaAuthinfoDAOImpl extends BaseDao implements SinaAuthinfoDAO {

    public SinaAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        SinaAuthinfo _key = new SinaAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_sina_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(SinaAuthinfo record) {
        getSqlMapClientTemplate().insert("t_sina_authinfo.insert", record);
    }

    public void insertSelective(SinaAuthinfo record) {
        getSqlMapClientTemplate().insert("t_sina_authinfo.insertSelective", record);
    }

    public SinaAuthinfo selectByPrimaryKey(Integer id) {
        SinaAuthinfo _key = new SinaAuthinfo();
        _key.setId(id);
        SinaAuthinfo record = (SinaAuthinfo) getSqlMapClientTemplate().queryForObject("t_sina_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(SinaAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_sina_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(SinaAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_sina_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<SinaAuthinfo> getListByUserAndCredit(SinaAuthinfo userInfo) {
        return getSqlMapClientTemplate().queryForList("t_sina_authinfo.getListByUserAndCredit",userInfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<SinaAuthinfo> getSinaAuthinfoByUserid(SinaAuthinfo sinaAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_sina_authinfo.getSinaAuthinfoByUserid",sinaAuthinfo); //To change body of implemented methods use File | Settings | File Templates.
    }
}