package cn.emagsoftware.xfb.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.emagsoftware.frame.bean.BaseRspBean;
import cn.emagsoftware.frame.controller.BaseController;
import cn.emagsoftware.frame.exception.BaseException;
import cn.emagsoftware.pay.alipay.util.AlipaySubmit;
import cn.emagsoftware.pay.alipay.util.UtilDate;
import cn.emagsoftware.pay.rsa.alipay.util.AlipayNotify;
import cn.emagsoftware.utils.CommonUtils;
import cn.emagsoftware.utils.ConfigCache;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.WcfCallUtils;
import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.AppConstants;
import cn.emagsoftware.xfb.constants.BillConstant;
import cn.emagsoftware.xfb.constants.SysUserConstant;
import cn.emagsoftware.xfb.dto.PayInfoRep;
import cn.emagsoftware.xfb.pojo.BillStage;
import cn.emagsoftware.xfb.pojo.MemberInfo;
import cn.emagsoftware.xfb.pojo.OrderInfo;
import cn.emagsoftware.xfb.service.BillStageService;
import cn.emagsoftware.xfb.service.MemberInfoService;
import cn.emagsoftware.xfb.service.OrderService;
import cn.tyiti.xfb.bojo.PayRecode;
import cn.tyiti.xfb.service.IPayRecodeService;
import cn.tyiti.xfb.utils.ValidPayUtil;

/**
 * 支付宝相关接口
 */
@Controller
@RequestMapping(value = "/aliPay")
public class AliPayController extends BaseController {

    @Autowired
    private BillStageService billStageService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemcachedClient memcachedClient;
    
    @Autowired
	private IPayRecodeService payRecodeService;
    
    //    支付宝网关地址
    String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
    ////////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////

    //返回格式
    String format = "xml";
    //必填，不需要修改

    //返回格式
    String v = "2.0";
    //必填，不需要修改

    //请求号
    String req_id = UtilDate.getOrderNum();
    //必填，须保证每次请求都是唯一

    //req_data详细信息

    //服务器异步通知页面路径
//    String notify_url = "http://localhost:8081/xfbInterface/notify_url.jsp";
    String notify_url = ConfigCache.getNotifyUrl();
    //需http://格式的完整路径，不能加?id=123这类自定义参数

    //页面跳转同步通知页面路径
//    String call_back_url = "http://127.0.0.1:8081/xfbInterface/call_back_url.jsp";
    String call_back_url = ConfigCache.getReturnUrl();
    //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

    //操作中断返回地址
//    String merchant_url = "http://127.0.0.1:8081/xfbInterface/xxxxxx.jsp";
    String merchant_url = ConfigCache.getErrorNotifyUrl();
    //用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数


    /**
     * 支付宝授权与交易
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/callPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String callPay(HttpServletResponse response, Map<String, String> model) {
        log.info("AliPayController.callPay,parameter[{trade_no,subject,total_fee},{"
                + request.getParameter("trade_no")
                + ","
                + request.getParameter("subject")
                + ","
                + request.getParameter("total_fee") + "}]");
        PayInfoRep payInfoRep = new PayInfoRep();
//        String ret = RET_JSP;
        try {
            //商户订单号
            String out_trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //商户网站订单系统中唯一订单号，必填
            //订单名称
            String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"), "UTF-8");
            //必填
            //付款金额
            String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
            //必填
            //请求业务参数详细
            String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url + "</call_back_url><seller_account_name>" + ConfigCache.getSellerEmail() + "</seller_account_name><out_trade_no>" + out_trade_no + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
            //必填

            //////////////////////////////////////////////////////////////////////////////////

            //把请求参数打包成数组
            Map<String, String> sParaTempToken = new HashMap<String, String>();
            sParaTempToken.put("service", "alipay.wap.trade.create.direct");
            sParaTempToken.put("partner", ConfigCache.getPartner());
            sParaTempToken.put("_input_charset", ConfigCache.getInputCharset());
            sParaTempToken.put("sec_id", ConfigCache.getSignType());
            sParaTempToken.put("format", format);
            sParaTempToken.put("v", v);
            sParaTempToken.put("req_id", req_id);
            sParaTempToken.put("req_data", req_dataToken);


            //建立请求
            String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
            //URLDECODE返回的信息
            sHtmlTextToken = URLDecoder.decode(sHtmlTextToken, ConfigCache.getInputCharset());
            //获取token
            String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);

            ////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////

            //业务详细
            String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
            //必填

            //把请求参数打包成数组
            Map<String, String> sParaTemp = new HashMap<String, String>();
            sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
            sParaTemp.put("partner", ConfigCache.getPartner());
            sParaTemp.put("_input_charset", ConfigCache.getInputCharset());
            sParaTemp.put("sec_id", ConfigCache.getSignType());
            sParaTemp.put("format", format);
            sParaTemp.put("v", v);
            sParaTemp.put("req_data", req_data);

            //建立请求
            String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
            if (sHtmlText != null) {
                response.getWriter().write(sHtmlText);
                log.debug("支付宝授权接口调用完成");
            } else {
                  return "pay/alipayPrepareError";
            }


        } catch (BaseException e) {
            payInfoRep.setResultCode(e.getErrorCode());
            payInfoRep.setResultMessage(e.getErrorMsg());
        } catch (Exception e) {
            log.error("AliPayController.callPay", e);
            payInfoRep.setResultCode(Constant.ERROR_CODE_9999);
            payInfoRep.setResultMessage(Constant.ERROR_MESSAGE
                    .get(Constant.ERROR_CODE_9999));
        }
//        try {
//            model.put(Constant.RETURN_MESSAGE, JsonUtils.getJSONString(payInfoRep));
//        } catch (Exception e) {
//            log.error("AppSysController.appHeartbeat", e);
//        }
        return null;
    }



    /**
     * 支付宝授权与交易
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/notifyPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String notifyPay(HttpServletResponse response, Map<String, String> model) {
        String orderNo = request.getParameter("out_trade_no");//获取流水号
        float total_fee = Float.valueOf((request.getParameter("total_fee"))); //获取总金额
       
    	log.info("<<<<<<<<<<<<<notifyPay.do 支付宝还款开始>>>>>>>>>>>>>>>>>>");
        log.info("AliPayController.notifyPay,parameter[{trade_no,subject,total_fee,out_trade_no},{"
                + request.getParameter("trade_no")  		//支付宝交易号
                + "," + request.getParameter("subject") 	//商品名称、订单名称
                + ","+ total_fee + "," + orderNo +  "}]");
        try {
        	if(this.validInfoFromAli(orderNo) && validInfoIsSuccess(orderNo)){
        		List<BillStage> billList = billStageService.getBillListByTradeNo(orderNo);
        		if(ValidPayUtil.validBillStages(billList, total_fee,orderNo)){
        			payRecodeService.updatePayStateByTradeNo(orderNo, BillConstant.BILL_PAY_CHANNEL_1, billList);
        			log.info("流水号"+orderNo+"还款成功！");
        			response.getWriter().write("success");
        		}else{
        			logInfoError("还款失败>>>>开始更新交易流水表状态："+orderNo);//更新交易流水表状态
                    payRecodeService.updatePayState(new PayRecode(orderNo, PayRecode.PAY_RECODE_STATE_A3));
                    logInfoError("交易流水表状态已更改为"+PayRecode.PAY_RECODE_STATE_A3+":异步通知校验失败="+PayRecode.PAY_RECODE_STATE_A3);
                    response.getWriter().write("fail");//fail
        		}
        	}
        } catch (Exception e) {
            log.error("AliPayController.notifyPay", e);
        }
        log.info("<<<<<<<<<<<<<支付宝还款结束>>>>>>>>>>>>>>>>>>");
        return null;
    }
    
    /**
     * 打印校验还款金额及签名信息info及error日志
     * @author KELLEN
     * @param msg 
     */
    private void logInfoError(String msg){
    	log.info(msg);//打印info日志
    	log.error(msg);//打印error日志
    }
    
    /**
     * 校验还款金额及签名信息成功
     * @author KELLEN
     * @param orderNo 流水号
     * */
    public boolean validInfoIsSuccess(String orderNo){
    	 String trade_status = request.getParameter("trade_status");	 //交易状态
    	 if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS"))
    		 return true;
    	 else{
    		 log.info("流水号："+orderNo+"支付未成功");
    		 return false;
    	 }
    }
    
    /**
     * 验证消息是否是支付宝发出的合法消息
     * @author KELLEN
     * @param orderNo 流水号
     * */
    private boolean validInfoFromAli(String orderNo){
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "GBK");
            params.put(name, valueStr);
        }
        boolean result = AlipayNotify.verify(params);//计算得出通知验证结果
        if(!result)
        	this.logInfoError("流水号："+orderNo+"返回参数为不合法参数");
        return result;
    }
}


