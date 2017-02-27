package cn.tyiti.xfb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.tyiti.xfb.bojo.ImageInfo;
import cn.tyiti.xfb.bojo.JobInfo;
import cn.tyiti.xfb.constant.BankInfoConstant;
import cn.tyiti.xfb.service.ImageInfoService;
/**
 * 
 * 驾照Controller.
 * 
 * @version 1.0 2015-8-28
 * @author Black
 */
@Controller
@RequestMapping(value = "/licenceInfo")
public class LicenceInfoController extends BaseController{
	
	@Autowired
	private ImageInfoService imageInfoService;
	
	/**
	 * 提交驾照信息
	 * @version 1.0 2015-8-28
	 * @author Black
	 */
	@RequestMapping(value = "/savelicenceInfo", method = {RequestMethod.POST})
    public String savelicenceInfo(Map<String, String> model) {
		BaseRspBean response = new BaseRspBean();
		ImageInfo imageInfo = this.getParamConvertEntity(ImageInfo.class);
        try {	
        	//驾照
//        	imageInfo.setType("A6");
        	imageInfo.setType("'A6','B6'");
        	imageInfoService.commitImageInfo(imageInfo);
    		//成功设置
        	response.setCodeAndMsg(Constant.SUCCESS_CODE,Constant.ERROR_MESSAGE
                    .get(Constant.SUCCESS_CODE));
        } catch (Exception ex) {
            log.error("ImageInfoController.commitImageInfo", ex);
            response.setCodeAndMsg(Constant.ERROR_CODE_9999, Constant.ERROR_MESSAGE.get(Constant.ERROR_CODE_9999));
        }
    	model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
        return RET_JSP;
    }
	
}
