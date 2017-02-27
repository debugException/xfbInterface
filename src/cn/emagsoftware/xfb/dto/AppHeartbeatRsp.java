package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;

public class AppHeartbeatRsp extends BaseRspBean {
    /**
     * 心跳会话的唯一标识
     */
    private String appUUID;

    public String getAppUUID() {
        return appUUID;
    }

    public void setAppUUID(String appUUID) {
        this.appUUID = appUUID;
    }


}
