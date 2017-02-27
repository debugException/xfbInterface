package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.TaobaoAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.TaobaoAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("taobaoAuthinfoDAO")
public class TaobaoAuthinfoDAOImpl extends BaseDao implements TaobaoAuthinfoDAO {

    public TaobaoAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        TaobaoAuthinfo _key = new TaobaoAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_taobao_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(TaobaoAuthinfo record) {
        getSqlMapClientTemplate().insert("t_taobao_authinfo.insert", record);
    }

    public void insertSelective(TaobaoAuthinfo record) {
        getSqlMapClientTemplate().insert("t_taobao_authinfo.insertSelective", record);
    }

    public TaobaoAuthinfo selectByPrimaryKey(Integer id) {
        TaobaoAuthinfo _key = new TaobaoAuthinfo();
        _key.setId(id);
        TaobaoAuthinfo record = (TaobaoAuthinfo) getSqlMapClientTemplate().queryForObject("t_taobao_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(TaobaoAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_taobao_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TaobaoAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_taobao_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<TaobaoAuthinfo> getListByUserAndCredit(TaobaoAuthinfo userInfo) {
        return getSqlMapClientTemplate().queryForList("t_taobao_authinfo.getListByUserAndCredit",userInfo);
         //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TaobaoAuthinfo> getTaobaoAuthinfoByUserid(TaobaoAuthinfo taobaoAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_taobao_authinfo.getTaobaoAuthinfoByUserid",taobaoAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }
}