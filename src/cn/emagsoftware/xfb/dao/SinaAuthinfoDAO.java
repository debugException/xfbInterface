package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.SinaAuthinfo;

import java.util.List;

public interface SinaAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(SinaAuthinfo record);

    void insertSelective(SinaAuthinfo record);

    SinaAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaAuthinfo record);

    int updateByPrimaryKey(SinaAuthinfo record);

    List<SinaAuthinfo> getListByUserAndCredit(SinaAuthinfo userInfo);

    List<SinaAuthinfo> getSinaAuthinfoByUserid(SinaAuthinfo sinaAuthinfo);
}