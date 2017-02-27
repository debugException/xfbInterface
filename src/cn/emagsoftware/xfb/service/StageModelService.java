package cn.emagsoftware.xfb.service;

import cn.emagsoftware.xfb.pojo.StageModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-23
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public interface StageModelService {
    public List<StageModel> getStageList()throws Exception;
    
    /**
     * 根据分期数获取分期模型
     * @param stageNum
     * @return
     */
	public StageModel getStageModelByStageNum(int stageNum);
}
