package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.SysLogDAO;
import cn.emagsoftware.xfb.pojo.SysLog;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("sysLogDAO")
public class SysLogDAOImpl extends BaseDao implements SysLogDAO {

    public SysLogDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        SysLog _key = new SysLog();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_sys_log.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(SysLog record) {
        getSqlMapClientTemplate().insert("t_sys_log.insert", record);
    }

    public void insertSelective(SysLog record) {
        getSqlMapClientTemplate().insert("t_sys_log.insertSelective", record);
    }

    public SysLog selectByPrimaryKey(Integer id) {
        SysLog _key = new SysLog();
        _key.setId(id);
        SysLog record = (SysLog) getSqlMapClientTemplate().queryForObject("t_sys_log.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(SysLog record) {
        int rows = getSqlMapClientTemplate().update("t_sys_log.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(SysLog record) {
        int rows = getSqlMapClientTemplate().update("t_sys_log.updateByPrimaryKey", record);
        return rows;
    }
}
