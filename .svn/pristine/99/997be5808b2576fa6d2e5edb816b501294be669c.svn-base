package cn.tyiti.xfb.controller.blacklistmanage;   

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.BillConstant;
import cn.tyiti.xfb.utils.jpushUtil.JpushMessage;

/** 
 * 创建时间：2015-12-9 下午6:08:12  
 * 项目名称：xfbInterface  
 * @author liminghua  
 * @version 1.0   
 * @since JDK 1.6
 * 文件名称：JpushController.java  
 * 类说明：  
 */
@Controller
@RequestMapping(value = "/jpushController")
public class JpushController extends BaseController {

	@Autowired
	private JpushMessage jpushMessage;
	
	/**
	 * <p>Title:jpushMessage</p>
	 * <p>Description:</p>
	 * @param model json params {"pushInfo":[{"title":"信分宝","message":"message","userIds":["1032","1039"]}]}
	 * @return 
	 */
	@RequestMapping(value = "/jpushMessage", method = {RequestMethod.POST, RequestMethod.GET})
	public String jpushMessage(Map<String, String> model) {
		BaseRspBean response = new BaseRspBean();
		try {
			String jpushmessage = "";
			
			jpushmessage = request.getParameter("pushInfo");
			log.info("推送开始------>json:"+jpushmessage);
	    	if(null!=jpushmessage&&!"".equals(jpushmessage)){
				JSONObject dataJson = JSONObject.fromObject(jpushmessage);
				
				JSONArray pushInfoArray = dataJson.getJSONArray("pushInfo");
				
				Object[] pushInfoObj = pushInfoArray.toArray();  
				//推送成功个数
				int jpushNum = 0;
				for(int i=0;i<pushInfoObj.length;i++){
					JSONObject  jsonObject = (JSONObject) pushInfoObj[i];
	
					String title = jsonObject.getString("title");
			    	String message = jsonObject.getString("message");
			    	
			    	JSONArray dataArray = jsonObject.getJSONArray("userIds");
			    	Object[] obj =dataArray.toArray();  
			    	Set<String> userIds = new HashSet<String>();
			    	for(int j=0;j<obj.length;j++){  
			    	    userIds.add((String)obj[i]);
			    	} 
			    	//调用推送
			    	log.info("极光调用开始：----->"+"title:"+title+"message:"+message+"userids"+userIds);
			    	Long msgId = jpushMessage.jpushMessageByAlias(title, message, userIds);
			    	if(0!=msgId){
			    		jpushNum ++;
			    	}
				}
				
				if(pushInfoObj.length==jpushNum){
					response.setCodeAndMsg(Constant.SUCCESS_CODE, "全部推送到服务器成功");
					log.info("推送结束全部推送成功");
				}else{
					response.setCodeAndMsg(Constant.ERROR_CODE_9999,(pushInfoObj.length-jpushNum)+"个推送失败");
					log.error("推送部分失败,json-->"+jpushmessage);
				}
	    	}else {
	    		response.setCodeAndMsg(Constant.ERROR_CODE_9999,"参数为空");
			}
		} catch (Exception ex) {
	        log.error("推送异常", ex);
	        response.setResultCode(BillConstant.ERROR_CODE_2000);
	    }

        try {
            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(response));
            log.info("推送返回结果"+JsonUtils.getJSONString(response));
        } catch (Exception e) {
            log.error("推送返回异常", e);
        }
        return RET_JSP;
	}
}