package cn.emagsoftware.xfb.dto;

import java.util.Map;

import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * 费率数据Entity
 *
 * @author Black
 * @date 2015-07-16
 */
public class RateDateRsp extends BaseRspBean {

	//费率数据
    private Map stageModelMap;

	public Map getStageModelMap() {
		return stageModelMap;
	}

	public void setStageModelMap(Map stageModelMap) {
		this.stageModelMap = stageModelMap;
	}

}
