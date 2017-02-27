package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;

/**
 * 订单信息
 *
 */
public class SMSCodeRep extends BaseRspBean {

    private String SMSCode;

    public String getSMSCode() {
        return SMSCode;
    }

    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }
}
