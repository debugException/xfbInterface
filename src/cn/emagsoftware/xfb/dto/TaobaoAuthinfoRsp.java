package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.TaobaoAuthinfo;

/**
 * 订单列表
 *
 */
public class TaobaoAuthinfoRsp extends BaseRspBean {

    private TaobaoAuthinfo  taobaoAuthinfo;

    public TaobaoAuthinfo getTaobaoAuthinfo() {
        return taobaoAuthinfo;
    }

    public void setTaobaoAuthinfo(TaobaoAuthinfo taobaoAuthinfo) {
        this.taobaoAuthinfo = taobaoAuthinfo;
    }




}
