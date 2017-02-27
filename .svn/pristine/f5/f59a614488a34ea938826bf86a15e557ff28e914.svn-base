package cn.emagsoftware.xfb.dao.impl;

import cn.emagsoftware.frame.dao.BaseDao;
import cn.emagsoftware.xfb.dao.StageModelDAO;
import cn.emagsoftware.xfb.pojo.StageModel;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stageModelDAO")
public class StageModelDAOImpl extends BaseDao implements StageModelDAO {

    public StageModelDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        StageModel _key = new StageModel();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("t_stage_model.deleteByPrimaryKey", _key);
        return rows;
    }

    public void insert(StageModel record) {
        getSqlMapClientTemplate().insert("t_stage_model.insert", record);
    }

    public void insertSelective(StageModel record) {
        getSqlMapClientTemplate().insert("t_stage_model.insertSelective", record);
    }

    public StageModel selectByPrimaryKey(Integer id) {
        StageModel _key = new StageModel();
        _key.setId(id);
        StageModel record = (StageModel) getSqlMapClientTemplate().queryForObject("t_stage_model.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByPrimaryKeySelective(StageModel record) {
        int rows = getSqlMapClientTemplate().update("t_stage_model.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(StageModel record) {
        int rows = getSqlMapClientTemplate().update("t_stage_model.updateByPrimaryKey", record);
        return rows;
    }

    @Override
    public List<StageModel> getStageList() {
        return  getSqlMapClientTemplate().queryForList("t_stage_model.getStageList");  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public StageModel getStageModelByStageNum(int stageNum) {
		return (StageModel) getSqlMapClientTemplate().queryForObject("t_stage_model.getStageModelByStageNum", stageNum);
	}
}