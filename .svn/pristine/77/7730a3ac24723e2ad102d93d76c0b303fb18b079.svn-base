package cn.emagsoftware.xfb.dao;

import cn.emagsoftware.xfb.pojo.StageModel;

import java.util.List;

public interface StageModelDAO {
    int deleteByPrimaryKey(Integer id);

    void insert(StageModel record);

    void insertSelective(StageModel record);

    StageModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StageModel record);

    int updateByPrimaryKey(StageModel record);

    List<StageModel> getStageList();
    /**
     * 根据分期数获取分期模型
     * @param stageNum
     * @return
     */
	StageModel getStageModelByStageNum(int stageNum);

}