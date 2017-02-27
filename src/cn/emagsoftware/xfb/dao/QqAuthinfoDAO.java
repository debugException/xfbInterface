package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.QqAuthinfo;

import java.util.List;

public interface QqAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(QqAuthinfo record);

    void insertSelective(QqAuthinfo record);

    QqAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QqAuthinfo record);

    int updateByPrimaryKey(QqAuthinfo record);

    List<QqAuthinfo> getListByUserAndCredit(QqAuthinfo userInfo);

    List<QqAuthinfo> getQqAuthinfoByUserid(QqAuthinfo qqAuthinfo);
}