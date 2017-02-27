package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.SysLog;

public interface SysLogDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(SysLog record);

    void insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}
