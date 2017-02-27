package cn.emagsoftware.xfb.pojo;

import cn.emagsoftware.frame.bean.BaseBean;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-21
 * Time: 下午9:36
 * To change this template use File | Settings | File Templates.
 */
public class StageRate extends BaseBean {


    private int stageNum;

    private Float stageRate;

    private Float stageAount;

    public int getStageNum() {
        return stageNum;
    }

    public void setStageNum(int stageNum) {
        this.stageNum = stageNum;
    }

    public Float getStageRate() {
        return stageRate;
    }

    public void setStageRate(Float stageRate) {
        this.stageRate = stageRate;
    }

    public Float getStageAount() {
        return stageAount;
    }

    public void setStageAount(Float stageAount) {
        this.stageAount = stageAount;
    }
}
