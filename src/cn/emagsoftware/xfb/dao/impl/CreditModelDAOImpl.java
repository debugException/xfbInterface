package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.CreditModelDAO;
import cn.emagsoftware.xfb.pojo.CreditModel;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("creditModelDAO")
public class CreditModelDAOImpl extends BaseDao implements CreditModelDAO {

    public CreditModelDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        CreditModel _key = new CreditModel();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_credit_model.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(CreditModel record) {
        getSqlMapClientTemplate().insert("t_credit_model.insert", record);
    }

    public void insertSelective(CreditModel record) {
        getSqlMapClientTemplate().insert("t_credit_model.insertSelective", record);
    }

    public CreditModel selectByPrimaryKey(Integer id) {
        CreditModel _key = new CreditModel();
        _key.setId(id);
        CreditModel record = (CreditModel) getSqlMapClientTemplate().queryForObject("t_credit_model.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(CreditModel record) {
        int rows = getSqlMapClientTemplate().update("t_credit_model.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(CreditModel record) {
        int rows = getSqlMapClientTemplate().update("t_credit_model.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<CreditModel> getCreditModel() {
        return  getSqlMapClientTemplate().queryForList("t_credit_model.getCreditModel");  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<CreditModel> getCreditModelByType(CreditModel creditModel) {
        return  getSqlMapClientTemplate().queryForList("t_credit_model.getCreditModelByType",creditModel); //To change body of implemented methods use File | Settings | File Templates.
    }
}