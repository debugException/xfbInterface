package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.DriveAuthinfoDAO;
import cn.emagsoftware.xfb.pojo.DriveAuthinfo;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("driveAuthinfoDAO")
public class DriveAuthinfoDAOImpl extends BaseDao implements DriveAuthinfoDAO {

    public DriveAuthinfoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        DriveAuthinfo _key = new DriveAuthinfo();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_drive_authinfo.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(DriveAuthinfo record) {
        getSqlMapClientTemplate().insert("t_drive_authinfo.insert", record);
    }

    public void insertSelective(DriveAuthinfo record) {
        getSqlMapClientTemplate().insert("t_drive_authinfo.insertSelective", record);
    }

    public DriveAuthinfo selectByPrimaryKey(Integer id) {
        DriveAuthinfo _key = new DriveAuthinfo();
        _key.setId(id);
        DriveAuthinfo record = (DriveAuthinfo) getSqlMapClientTemplate().queryForObject("t_drive_authinfo.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(DriveAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_drive_authinfo.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(DriveAuthinfo record) {
        int rows = getSqlMapClientTemplate().update("t_drive_authinfo.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<DriveAuthinfo> getDriveAuthinfoByUserid(DriveAuthinfo driveAuthinfo) {
         //To change body of implemented methods use File | Settings | File Templates.
        return   getSqlMapClientTemplate().queryForList("t_drive_authinfo.getDriveAuthinfoByUserid",driveAuthinfo);
    }

    @Override
    public void driveAuthinfo(DriveAuthinfo requestDriveAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_drive_authinfo.driveAuthinfo", requestDriveAuthinfo);
    }

    @Override
    public void updateImagePathByUserId(DriveAuthinfo driveAuthinfo) {
        //To change body of implemented methods use File | Settings | File Templates.
        getSqlMapClientTemplate().update("t_drive_authinfo.updateImagePathByUserId", driveAuthinfo);
    }
}