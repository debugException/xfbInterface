package cn.emagsoftware.xfb.service;

import cn.emagsoftware.xfb.pojo.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 15-4-22
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public interface CreditModelService {


    public CreditModel getCreditModelById(Integer creditId)throws Exception;


    public  List<CreditModel> getCreditModel()throws Exception;


    public CreditModel getCreditModelByType(Integer type)throws Exception;

    public List<CreditModel> getCreditModelListByType(Integer type)throws Exception;
}
