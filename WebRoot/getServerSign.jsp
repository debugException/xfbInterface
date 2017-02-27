<%@ page import="cn.emagsoftware.utils.AppUtils" %>
<%@ page import="cn.emagsoftware.utils.CipherUtils" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@page contentType="text/json" session="false" %>
<%
    String remoteAddress = request.getRemoteAddr();
    boolean isValidateIp = true;
    System.out.println("remoteAddress: " + remoteAddress);
    /* for(String ip:new String[]{"192.168","127.0.","0:0:0","223.68.","112.86.134.35"}){
        if(remoteAddress.startsWith(ip)){
            isValidateIp = true;
            break;
        }
      } */
    System.out.println("isValidateIp: " + isValidateIp);
    if(!isValidateIp){
        response.setStatus(403);
        return;
    }
    String requestUri = request.getContextPath() + "/"+request.getHeader("_system_uri");
	System.out.println("requestUri:"+requestUri);
    String timestamp = request.getHeader("_system_timestamp");
	System.out.println("timestamp:"+timestamp);
    WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletConfig().getServletContext());
    String requestRawValue = AppUtils.createRequestSignRawValue(request,null);
	System.out.println("requestRawValue:"+requestRawValue);
    String clientKey = null == request.getHeader("_system_clientKey") ? "" : request.getHeader("_system_clientKey");
	System.out.println("clientKey:"+clientKey);
    String appId = request.getParameter("appId");
    String appKey = request.getHeader("_system_appKey");
	System.out.println("appId:"+appId);
	System.out.println("appKey:"+appKey);

    String serverSignValue = CipherUtils.md5Request(requestUri, timestamp, requestRawValue, clientKey, appKey);
    out.clear();
    out.write("{\"signValue\":\""+ serverSignValue + "\"}");
%>