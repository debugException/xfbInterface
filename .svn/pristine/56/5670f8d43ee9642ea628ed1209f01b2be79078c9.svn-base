package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.MarryAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.MarryAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("marryAuthinfoDAO")
public class MarryAuthinfoDAOImpl extends BaseDao implements MarryAuthinfoDAO {

    public MarryAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        MarryAuthinfo _key = new MarryAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_marry_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(MarryAuthinfo record) {
        getSqlMapClientTemplate().insert("t_marry_authinfo.insert", record);
    }

    public void insertSelective(MarryAuthinfo record) {
        getSqlMapClientTemplate().insert("t_marry_authinfo.insertSelective", record);
    }

    public MarryAuthinfo selectByPrimaryKey(Integer id) {
        MarryAuthinfo _key = new MarryAuthinfo();
        _key.setId(id);
        MarryAuthinfo record = (MarryAuthinfo) getSqlMapClientTemplate().queryForObject("t_marry_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(MarryAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_marry_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(MarryAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_marry_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<MarryAuthinfo> getMarryAuthinfByUserid(MarryAuthinfo marryAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_marry_authinfo.getMarryAuthinfByUserid",marryAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    public void marryAuthinfo(MarryAuthinfo requestMarryAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_marry_authinfo.marryAuthinfo", requestMarryAuthinfo);
    }


    @Override
    public void updateImagePathByUserId(MarryAuthinfo requestMarryAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_marry_authinfo.updateImagePathByUserId", requestMarryAuthinfo);
    }
}