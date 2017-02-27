package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.QqAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.QqAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qqAuthinfoDAO")
public class QqAuthinfoDAOImpl extends BaseDao implements QqAuthinfoDAO {

    public QqAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        QqAuthinfo _key = new QqAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_qq_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(QqAuthinfo record) {
        getSqlMapClientTemplate().insert("t_qq_authinfo.insert", record);
    }

    public void insertSelective(QqAuthinfo record) {
        getSqlMapClientTemplate().insert("t_qq_authinfo.insertSelective", record);
    }

    public QqAuthinfo selectByPrimaryKey(Integer id) {
        QqAuthinfo _key = new QqAuthinfo();
        _key.setId(id);
        QqAuthinfo record = (QqAuthinfo) getSqlMapClientTemplate().queryForObject("t_qq_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(QqAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_qq_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(QqAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_qq_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<QqAuthinfo> getListByUserAndCredit(QqAuthinfo userInfo) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<QqAuthinfo> getQqAuthinfoByUserid(QqAuthinfo qqAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_qq_authinfo.getQqAuthinfoByUserid",qqAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }
}