package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.TaobaoAuthinfo;

import java.util.List;

public interface TaobaoAuthinfoDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(TaobaoAuthinfo record);

    void insertSelective(TaobaoAuthinfo record);

    TaobaoAuthinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaobaoAuthinfo record);

    int updateByPrimaryKey(TaobaoAuthinfo record);

    List<TaobaoAuthinfo> getListByUserAndCredit(TaobaoAuthinfo userInfo);

    List<TaobaoAuthinfo> getTaobaoAuthinfoByUserid(TaobaoAuthinfo taobaoAuthinfo);
}