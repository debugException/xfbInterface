package cn.emagsoftware.utils;

import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.ILogFactory;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件相关缓存
 */
public final class ConfigCache {

    private static ILog log = ILogFactory.getILogInstance(ConfigCache.class);

    /**
     * 客户端接口请求超时时间（ms）
     */
    private static int httpClientSoTimeout;

    /**
     * 请求客户端接口请求连接等待时间（ms）
     */
    private static int httpClientConnectTimeout;

    /**
     * 文件资源地址
     */
    private static String resourceTomcatPath;

    /**
     * 图片资源根路径
     */
    private static String pictureRootPath;

    private static String clientKey;

    private static Map<String, String> appKeyMap = new HashMap<String, String>();

    /**
     * 配置文件信息
     */
    private static Map<String, String> configMap;
    /**
     *  接口版本 0 测试版本不做数据校验 1 商用版本数据校验
     */
    private static int INTER_VERSION = 0;

    /**
     *  接口短信 0 短信网关没有不做短信发送 1 进行短信发送
     */
    private static int INTER_SMS = 0;

    /**
     * 连接上一个url，获取response的返回等待时间
     */
    private static int SO_TIMEOUT;

    /**
     * 连接一个url的连接等待时间
     */
    private static int CONNECT_TIMEOUT;
    /**
     * 合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    private static String PARTNER;

    /**
     * 商户的私钥
     */
    private static String KEY;

    /**
     * 字符编码格式 目前支持 gbk 或 utf-8
     */
    private static String INPUT_CHARSET;

    /**
     * 签名方式 不需修改
     */
    private static String SIGN_TYPE;

    /**
     * 客户端同一ip限制次数
     */
    private static int CLIENTIP_LIMIT;

    /**
     * 请求订单缓存大小
     */
    private static int TRADE_REQ_SIZE;

    /**
     * token缓存大小
     */
    private static int TOKEN_SIZE;

    /**
     * 服务器异步通知页面路径
     */
    private static String NOTIFY_URL;

    /**
     * 页面跳转同步通知页面路径
     */
    private static String RETURN_URL;

    /**
     * 请求出错时的通知页面路径
     */
    private static String ERROR_NOTIFY_URL;

    /**
     * 卖家支付宝账户号,支付宝强烈建议使用
     */
    private static String SELLER_ID;

    private static String SELLER_EMAIL;

    /**
     * 支付宝的通知IP集合，key:ip value:""
     */
    private static Map<String, String> alipayClientIpMap = new HashMap<String, String>();


        /**
         * 默认构造函数
         */
    private ConfigCache() {

    }

    /**
     * 读配置文件信息，存入缓存
     */
    public static void readConfigCache() {
        try {
            configMap = CommonUtils.getPropertiesConfig("config");
            if (null != configMap && !configMap.isEmpty()) {
                readHttpClientSoTime();
                readHttpClientConnTime();
                readClientKey();
//                readAppKey();
                readResourceTomcatPath();
                readInterVersion();
//                readInterSMS();
                readPictureRootPath();
//ali支付配置
                setPartner();
                setKey();
                setInputCharset();
                setSignType();
                setClientIpLimit();
                setTradeReqSize();
                setNotifyUrl();
                setReturnUrl();
                setErrorNotifyUrl();
                setSellerId();
                setAlipayClientIpMap();
                setTokenSize();
                setSellerEmail();
                setSoTimeOut();
                setConnectTimeOut();
            }
        } catch (Exception e) {
            log.error("ERROR:读取配置文件信息出错。", e);
        }
    }

    private static void readHttpClientConnTime() {
        String str = configMap.get("httpclient.connect.timeout");
        if (StringUtils.isEmpty(str)) {
            log.error("启动异常，调用请求服务端地址请求超时时间为空！");
        } else {
            httpClientConnectTimeout = Integer.parseInt(str);
            log.info("读配置文件，调用请求服务端地址超时的时间为：" + httpClientConnectTimeout);
        }
    }

    private static synchronized void readHttpClientSoTime() {
        String max = configMap.get("httpclient.so.timeout");
        if (BaseUtils.isNum(max)) {
            httpClientSoTimeout = Integer.parseInt(max);
            log.info("读配置文件，客户端接口请求超时时间（ms）为：" + httpClientSoTimeout);
        } else {
            log.error("启动异常，客户端接口请求超时时间（ms）为空或不合法！");
        }
    }

    private static synchronized void readClientKey() {
        String clientKeyStr = configMap.get("system.client.key");
        if (StringUtils.isNotBlank(clientKeyStr)) {
            clientKey = clientKeyStr;
            log.info("读配置文件，clientKey为：" + clientKey);
        } else {
            log.error("启动异常，clientKey为空或不合法！");
        }
    }

    private static synchronized void readAppKey() {
        String appKeyStr = configMap.get("system.app.key");
        if (StringUtils.isNotBlank(appKeyStr)) {
            String[] apps = appKeyStr.split(";");
            for (String appStr : apps) {
                String[] keys = appStr.split(":");
                appKeyMap.put(keys[0], keys[1]);
            }
            log.info("读配置文件，appKeyStr为：" + appKeyStr);
        } else {
            log.error("启动异常，appKeyStr为空或不合法！");
        }
    }

    private static synchronized void readResourceTomcatPath() {
        String imageUrl = configMap.get("resource.client.path");
        if (StringUtils.isNotBlank(imageUrl)) {
            resourceTomcatPath = imageUrl;
            log.info("读配置文件，resourceTomcatPath为：" + resourceTomcatPath);
        } else {
            log.error("启动异常，resourceTomcatPath为空或不合法！");
        }
    }

    private static synchronized void readPictureRootPath() {
        String pictRootPath = configMap.get("picture.root.path");
        if (StringUtils.isNotBlank(pictRootPath)) {
            pictureRootPath = pictRootPath;
            log.info("读配置文件，picture.root.path：" + pictureRootPath);
        } else {
            log.error("启动异常，pictRootPath为空或不合法！");
        }
    }

    /**
     * 获取客户端接口请求超时时间（ms）
     *
     * @return 客户端接口请求超时时间（ms）
     */
    public static int getHttpClientSoTimeout() {
        return httpClientSoTimeout;
    }

    /**
     * 获取请求客户端接口请求连接等待时间（ms）
     *
     * @return 请求客户端接口请求连接等待时间（ms）
     */
    public static int getHttpClientConnectTimeout() {
        return httpClientConnectTimeout;
    }

    public static String getClientKey() {
        return clientKey;
    }

    public static Map<String, String> getAppKeyMap() {
        return appKeyMap;
    }

    private static synchronized void readInterVersion() {
        String interVersionValue = configMap.get("inter.version");
        if (StringUtils.isNotBlank(interVersionValue)) {
            INTER_VERSION = Integer.parseInt(interVersionValue);
            log.info("读配置文件，interVersion：" + INTER_VERSION);
        } else {
            log.error("启动异常，interVersion为空或不合法！");
        }
    }

    private static synchronized void readInterSMS() {
        String interVersionValue = configMap.get("inter.SMS");
        if (StringUtils.isNotBlank(interVersionValue)) {
            INTER_SMS = Integer.parseInt(interVersionValue);
            log.info("读配置文件，INTER_SMS：" + INTER_SMS);
        } else {
            log.error("启动异常，INTER_SMS为空或不合法！");
        }
    }

    /**
     * 文件资源地址
     *
     * @return
     */
    public static String getResourceTomcatPath() {
        return resourceTomcatPath;
    }



    /**
     * 版本信息 0 测试版本 1 商用版本
     *
     * @return
     */
    public static int getInterVersion() {
        return INTER_VERSION;
    }

    /**
     * 版本信息 0 测试版本 1 商用版本
     *
     * @return
     */
    public static int getInterSMS() {
        return INTER_SMS;
    }

    /**
     * 获取
     *
     * @return
     */
    public static String getPictureRootPath() {
        return pictureRootPath;
    }

    /**
     * 设置合作身份者ID
     *
     */
    private static void setPartner() {
        String partner = configMap.get("partner");
        if (StringUtils.isEmpty(partner)) {
            log.error( "读配置文件异常，合作身份者ID为空！");
            return;
        }
        PARTNER = partner;
        log.info( "读配置文件，合作身份者ID为：" + partner);
    }

    public  static String getPartner(){
        return PARTNER;
    }

    public static String getKey() {
        return KEY;
    }


    /**
     * 设置商户的私钥
     */
    private static void setKey() {
        String key = configMap.get("key");
        if (StringUtils.isEmpty(key)) {
            log.error( "读配置文件异常，商户的私钥为空！");
            return;
        }
        KEY = key;
        log.info( "读配置文件，商户的私钥为：" + key);
    }

    public static String getInputCharset() {
        return INPUT_CHARSET;
    }

    /**
     * 设置字符编码格式
     *
     * 
     */
    private static void setInputCharset() {
        String input_charset = configMap.get("input_charset");
        if (StringUtils.isEmpty(input_charset)) {
            log.error( "读配置文件异常，字符编码格式为空！");
            return;
        }
        INPUT_CHARSET = input_charset;
        log.info( "读配置文件，字符编码格式为：" + input_charset);
    }


    /**
     * 设置签名方式
     *
     * 
     */
    private static void setSignType() {
        String sign_type = configMap.get("sign_type");
        if (StringUtils.isEmpty(sign_type)) {
            log.error( "读配置文件异常，签名方式为空！");
            return;
        }
        SIGN_TYPE = sign_type;
        log.info( "读配置文件，签名方式为：" + sign_type);
    }

    public static  String   getSignType(){
        return  SIGN_TYPE;
    }

    public static int getClientIpLimit() {
        return CLIENTIP_LIMIT;
    }

    /**
     * 设置客户端同一ip限制次数
     *
     * 
     */
    private static void setClientIpLimit() {
        int clientIpLimit = 0;
        try {
            clientIpLimit = Integer.parseInt(configMap.get("clientip_limit"));
        } catch (Exception e) {
            log.error( "读配置文件异常，客户端同一ip限制次数配置不合法，非数字！为" + configMap.get("clientip_limit"));
            return;
        }
        if (clientIpLimit <= 0) {
            log.error( "读配置文件异常，客户端同一ip限制次数必须为正整数！为" + configMap.get("clientip_limit"));
            return;
        }
        CLIENTIP_LIMIT = clientIpLimit;
        log.info( "读配置文件，客户端同一ip限制次数为：" + clientIpLimit);
    }

    public static int getTradeReqSize() {
        return TRADE_REQ_SIZE;
    }

    /**
     * 设置请求订单缓存大小
     *
     * @param
     */
    private static void setTradeReqSize() {
        int tradeReqSize = 0;
        try {
            tradeReqSize = Integer.parseInt(configMap.get("trade_request_size"));
        } catch (Exception e) {
            log.error( "读配置文件异常，请求订单缓存大小配置不合法，非数字！为" + configMap.get("trade_request_size"));
            return;
        }
        if (tradeReqSize <= 0) {
            log.error( "读配置文件异常，请求订单缓存大小必须为正整数！为" + configMap.get("trade_request_size"));
            return;
        }
        TRADE_REQ_SIZE = tradeReqSize;
        log.info( "读配置文件，请求订单缓存大小为：" + tradeReqSize);
    }


    public static String getNotifyUrl() {
        return NOTIFY_URL;
    }

    /**
     * 设置签名方式
     *
     * 
     */
    private static void setNotifyUrl() {
        String notifyUrl = configMap.get("notify_url");
        if (StringUtils.isEmpty(notifyUrl)) {
            log.error( "读配置文件异常，服务器异步通知页面路径为空！");
            return;
        }
        NOTIFY_URL = notifyUrl;
        log.info( "读配置文件，服务器异步通知页面路径为：" + notifyUrl);
    }

    public static String getReturnUrl() {
        return RETURN_URL;
    }

    /**
     * 设置
     *
     * 
     */
    private static void setReturnUrl() {
        String returnUrl = configMap.get("return_url");
        if (StringUtils.isEmpty(returnUrl)) {
            log.error( "读配置文件异常，页面跳转同步通知页面路径为空！");
            return;
        }
        RETURN_URL = returnUrl;
        log.info( "读配置文件，页面跳转同步通知页面路径为：" + returnUrl);
    }

    public static String getErrorNotifyUrl() {
        return ERROR_NOTIFY_URL;
    }

    private static void setErrorNotifyUrl() {
        String errorNotifyUrl = configMap.get("error_notify_url");
        if (StringUtils.isEmpty(errorNotifyUrl)) {
            log.error( "读配置文件异常，请求出错时的通知页面路径为空！");
            return;
        }
        ERROR_NOTIFY_URL = errorNotifyUrl;
        log.info( "读配置文件，请求出错时的通知页面路径为：" + errorNotifyUrl);
    }

    public static String getSellerId() {
        return SELLER_ID;
    }

    public static Map<String, String> getConfigMap() {
        return configMap;
    }


    /**
     * 设置
     *
     * 
     */
    private static void setSellerId() {
        String sellerId = configMap.get("seller_id");
        if (StringUtils.isEmpty(sellerId)) {
            log.error( "读配置文件异常，卖家支付宝账户号为空！");
            return;
        }
        SELLER_ID = sellerId;
        log.info( "读配置文件，卖家支付宝账户号为：" + sellerId);
    }

    public static Map<String, String> getAlipayClientIpMap() {
        return alipayClientIpMap;
    }

    public static void setAlipayClientIpMap() {
        String alipayClientIp = configMap.get("alipay_notice_ip");

        if (StringUtils.isEmpty(alipayClientIp)) {
            log.error( "读配置文件异常，支付宝的通知IP为空！");
            return;
        }
        String[] ips = alipayClientIp.split("\\|");
        for (String ip : ips) {
            alipayClientIpMap.put(ip.trim(), "");
        }
        log.info( "读配置文件，支付宝的通知IP为：" + alipayClientIp);

    }


    public static int getTokenSize() {
        return TOKEN_SIZE;
    }

    /**
     * 设置请求订单缓存大小
     *
     * @param
     */
    private static void setTokenSize() {
        int tokenSize = 0;
        try {
            tokenSize = Integer.parseInt(configMap.get("token_size"));
        } catch (Exception e) {
            log.error("读配置文件异常，token缓存大小配置不合法，非数字！为" + configMap.get("token_size"));
            return;
        }
        if (tokenSize <= 0) {
            log.error( "读配置文件异常，token缓存大小必须为正整数！为" + configMap.get("token_size"));
            return;
        }
        TOKEN_SIZE = tokenSize;
        log.info( "读配置文件，token缓存大小为：" + tokenSize);
    }

    public static String getSellerEmail() {
        return SELLER_EMAIL;
    }

    /**
     * 设置
     */
    private static void setSellerEmail() {
        String sellerEmail = configMap.get("seller_email");
        if (StringUtils.isEmpty(sellerEmail)) {
            log.error( "读配置文件异常，卖家支付宝账户邮箱号为空！");
            return;
        }
        SELLER_EMAIL = sellerEmail;
        log.info( "读配置文件，卖家支付宝账户邮箱号为：" + sellerEmail);
    }


    public static int getSoTimeOut() {
        return SO_TIMEOUT;
    }

    /**
     * 连接上一个url，获取response的返回等待时间配置
     *
     * @param
     */
    public static void setSoTimeOut() {
        int soTimeOut = 0;
        try {
            soTimeOut = Integer.parseInt(configMap.get("httpclient.so.timeout"));
        } catch (Exception e) {
            log.error( "读配置文件异常，连接上一个url，获取response的返回等待时间配置不合法，非数字！为" + configMap.get("httpclient.so.timeout"));
            return;
        }
        if (soTimeOut <= 0) {
            log.error( "读配置文件异常，连接上一个url，获取response的返回等待时间配置必须为正整数！为" + configMap.get("httpclient.so.timeout"));
            return;
        }
        SO_TIMEOUT = soTimeOut;
        log.info( "读配置文件，连接上一个url，获取response的返回等待时间配置大小为：" + soTimeOut);
    }

    public static int getConnectTimeOut() {
        return CONNECT_TIMEOUT;
    }

    /**
     * 连接一个url的连接等待时间
     *
     * @param
     */
    public static void setConnectTimeOut() {
        int connectTimeOut = 0;
        try {
            connectTimeOut = Integer.parseInt(configMap.get("httpclient.connect.timeout"));
        } catch (Exception e) {
            log.error( "读配置文件异常，连接一个url的连接等待时间配置不合法，非数字！为" + configMap.get("httpclient.connect.timeout"));
            return;
        }
        if (connectTimeOut <= 0) {
            log.error( "读配置文件异常，连接一个url的连接等待时间配置必须为正整数！为" + configMap.get("httpclient.connect.timeout"));
            return;
        }
        CONNECT_TIMEOUT = connectTimeOut;
        log.info( "读配置文件，连接一个url的连接等待时间配置大小为：" + connectTimeOut);
    }

}