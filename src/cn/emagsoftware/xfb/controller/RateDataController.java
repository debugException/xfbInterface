package cn.emagsoftware.xfb.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.dto.RateDateRsp;
import cn.emagsoftware.xfb.pojo.StageModel;
import cn.emagsoftware.xfb.service.StageModelService;

/**
 * 
 * 获取利率数据
 * 
 * @version 1.0 2015-7-17
 * @author Black
 */
@Controller
@RequestMapping(value = "/rateData")
public class RateDataController extends BaseController {

    @Autowired
    private StageModelService stageModelService;

    /**
     * 订单分期
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getRateData", method = {RequestMethod.GET, RequestMethod.POST})
    public String getRateData(Map<String, String> model) throws UnsupportedEncodingException {
    	log.info("-------------------获取费率数据信息-------------------");
    	RateDateRsp response = new RateDateRsp();
    	try {
    		
			List<StageModel> stageModelList = stageModelService.getStageList();
			//封装返回数据
			Map<String,Object> resultMap = new HashMap<String,Object>();
			for(StageModel stageModel:stageModelList){
				/*Map<String,Object> smMap = new HashMap<String,Object>();
				smMap.put("stageNum", stageModel.getStageNum());
				smMap.put("chargePercent", stageModel.getChargePercent());*/
				resultMap.put(String.valueOf(stageModel.getStageNum()),stageModel.getChargePercent());
			}
			response.setStageModelMap(resultMap);
    		//成功
            response.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
                    .get(Constant.SUCCESS_CODE));
		} catch (Exception ex) {
            log.error("VersionController.getVersion", ex);
            response.setCodeAndMsg(Constant.ERROR_CODE_9999,Constant.ERROR_MESSAGE
                    .get(Constant.ERROR_CODE_9999));
		}

    	try{
	    	model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
	
	        log.info("RateDataController.getRateData.response=="+JsonUtils.getJSONString(response));
	    } catch (Exception ex) {
	        log.error("RateDataController.getRateData", ex);
	    }
        return RET_JSP;
    }

}


