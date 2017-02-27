package cn.emagsoftware.xfb.service.impl;

import cn.emagsoftware.xfb.dao.CreditModelDAO;
import cn.emagsoftware.xfb.pojo.CreditModel;
import cn.emagsoftware.xfb.pojo.QqAuthinfo;
import cn.emagsoftware.xfb.pojo.SinaAuthinfo;
import cn.emagsoftware.xfb.pojo.TaobaoAuthinfo;
import cn.emagsoftware.xfb.service.CreditModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午7:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CreditModelServiceImpl implements CreditModelService {
    @Autowired
    private CreditModelDAO creditModelDAO;

    @Override
    public CreditModel getCreditModelById(Integer creditId) throws Exception {
        return creditModelDAO.selectByPrimaryKey(creditId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<CreditModel> getCreditModel() throws Exception {

        return creditModelDAO.getCreditModel();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CreditModel getCreditModelByType(Integer type) throws Exception {

        CreditModel creditModel = new CreditModel();
        creditModel.setTypePath(type+"");
        List<CreditModel>  list = creditModelDAO.getCreditModelByType(creditModel);

        if(list!=null&&list.size()>0){
            return list.get(0);
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<CreditModel> getCreditModelListByType(Integer type) throws Exception {
        CreditModel creditModel = new CreditModel();
        creditModel.setTypePath(type+"");
        List<CreditModel>  list = creditModelDAO.getCreditModelByType(creditModel);
        return list;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
