package cn.emagsoftware.xfb.dto;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.xfb.pojo.QqAuthinfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;

/**
 * 订单列表
 *
 */
public class QqAuthinfoRsp extends BaseRspBean {


    private QqAuthinfo qqAuthinfo;

    public QqAuthinfo getQqAuthinfo() {
        return qqAuthinfo;
    }

    public void setQqAuthinfo(QqAuthinfo qqAuthinfo) {
        this.qqAuthinfo = qqAuthinfo;
    }




}
