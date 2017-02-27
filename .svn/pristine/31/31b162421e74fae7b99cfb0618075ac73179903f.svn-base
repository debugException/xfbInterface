package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.xfb.dao.StageModelDAO;
import cn.emagsoftware.xfb.pojo.StageModel;
import cn.emagsoftware.xfb.service.StageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-23
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */

@Service
public class StageModelServiceImpl implements StageModelService {


    @Autowired
    private StageModelDAO stageModelDAO;

    @Override
    public List<StageModel> getStageList() throws Exception {
        return stageModelDAO.getStageList();  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public StageModel getStageModelByStageNum(int stageNum) {
		return stageModelDAO.getStageModelByStageNum(stageNum);
	}
}
