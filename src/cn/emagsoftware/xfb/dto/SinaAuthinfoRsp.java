package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.CreditModel;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;

import java.util.List;

/**
 * 订单列表
 *
 */
public class SinaAuthinfoRsp extends BaseRspBean {

  private SinaAuthinfo  sinaAuthinfo;

    public SinaAuthinfo getSinaAuthinfo() {
        return sinaAuthinfo;
    }

    public void setSinaAuthinfo(SinaAuthinfo sinaAuthinfo) {
        this.sinaAuthinfo = sinaAuthinfo;
    }
}
