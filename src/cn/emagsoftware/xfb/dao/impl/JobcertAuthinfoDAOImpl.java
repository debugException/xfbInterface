package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.JobcertAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.JobcertAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobcertAuthinfoDAO")
public class JobcertAuthinfoDAOImpl extends BaseDao implements JobcertAuthinfoDAO {

    public JobcertAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        JobcertAuthinfo _key = new JobcertAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_jobcert_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(JobcertAuthinfo record) {
        getSqlMapClientTemplate().insert("t_jobcert_authinfo.insert", record);
    }

    public void insertSelective(JobcertAuthinfo record) {
        getSqlMapClientTemplate().insert("t_jobcert_authinfo.insertSelective", record);
    }

    public JobcertAuthinfo selectByPrimaryKey(Integer id) {
        JobcertAuthinfo _key = new JobcertAuthinfo();
        _key.setId(id);
        JobcertAuthinfo record = (JobcertAuthinfo) getSqlMapClientTemplate().queryForObject("t_jobcert_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(JobcertAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_jobcert_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(JobcertAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_jobcert_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<JobcertAuthinfo> getJobcertAuthinfoByUserid(JobcertAuthinfo jobcertAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_jobcert_authinfo.getJobcertAuthinfoByUserid",jobcertAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void jobcertAuthinfo(JobcertAuthinfo requestJobcertAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_jobcert_authinfo.jobcertAuthinfo",requestJobcertAuthinfo);
    }

    @Override
    public List<JobcertAuthinfo> getJobcertAuthinfoByUseridAndType(JobcertAuthinfo jobcertAuthinfo) {
        return getSqlMapClientTemplate().queryForList("t_jobcert_authinfo.getJobcertAuthinfoByUseridAndType",jobcertAuthinfo);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateImagePathByUserId(JobcertAuthinfo jobcertAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_jobcert_authinfo.updateImagePathByUserId",jobcertAuthinfo);
    }
}