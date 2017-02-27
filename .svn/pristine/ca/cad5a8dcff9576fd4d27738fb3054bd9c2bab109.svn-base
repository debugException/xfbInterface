package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.BluecardAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.BluecardAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("bluecardAuthinfoDAO")
public class BluecardAuthinfoDAOImpl extends BaseDao implements BluecardAuthinfoDAO {

    public BluecardAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        BluecardAuthinfo _key = new BluecardAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_bluecard_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(BluecardAuthinfo record) {
        getSqlMapClientTemplate().insert("t_bluecard_authinfo.insert", record);
    }

    public void insertSelective(BluecardAuthinfo record) {
        getSqlMapClientTemplate().insert("t_bluecard_authinfo.insertSelective", record);
    }

    public BluecardAuthinfo selectByPrimaryKey(Integer id) {
        BluecardAuthinfo _key = new BluecardAuthinfo();
        _key.setId(id);
        BluecardAuthinfo record = (BluecardAuthinfo) getSqlMapClientTemplate().queryForObject("t_bluecard_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(BluecardAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_bluecard_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(BluecardAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_bluecard_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<BluecardAuthinfo> getBluecardAuthinfoByUserid(BluecardAuthinfo bluecardAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_bluecard_authinfo.getBluecardAuthinfoByUserid",bluecardAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void bluecardAuthinfo(BluecardAuthinfo requestBluecardAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_bluecard_authinfo.bluecardAuthinfo", requestBluecardAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(BluecardAuthinfo bluecardAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_bluecard_authinfo.updateImagePathByUserId", bluecardAuthinfo);
    }
}