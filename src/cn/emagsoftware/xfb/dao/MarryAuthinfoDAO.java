package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.MarryAuthinfo;

import java.util.List;

public interface MarryAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(MarryAuthinfo record);

    void insertSelective(MarryAuthinfo record);

    MarryAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarryAuthinfo record);

    int updateByPrimaryKey(MarryAuthinfo record);

    List<MarryAuthinfo> getMarryAuthinfByUserid(MarryAuthinfo marryAuthinfo);

    void marryAuthinfo(MarryAuthinfo requestMarryAuthinfo);


    public void updateImagePathByUserId(MarryAuthinfo requestMarryAuthinfo);
}