package cn.emagsoftware.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    /**
     * 日志KEY
     */
    public static final String LOG_ID = "ID";
    
	public static final String TIMESTAMP = "TIMESTAMP";

    /** UTF-8字符集编码 */
    public static final String CH_ENCODING_UTF8 = "UTF-8";

    /** GBK字符集编码 */
    public static final String CH_ENCODING_GBK = "GBK";

    /** 返回参数 */
    public static final String RETURN_MESSAGE = "retMsg";

    /** XML_HEAD */
    public static final String XML_HEAD = "<?xml version='1.0' encoding='UTF-8'?>";

    /** HTTP协议 Content-Type */
    public static final String HTTP_CONTENT_TYPE = "Content-Type";

    /** HTTP协议 Content-Type为application/x-www-form-urlencoded */
    public static final String HTTP_APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /** 成功返回码 */
    public static String SUCCESS_CODE = "0";
    
    public static String EMPTY = "";

    /** 空格符 */
    public static String SPACE = " ";

    /** 顿号 */
    public static String SIGN = "、";

    /** 逗号 */
    public static String COMMA = "，";

    /** 句号 */
    public static String PERIOD = "。 ";

    /** 半角英文分号 */
    public static String SEMICOLON = ";";

    /** 拼接符 */
    public static String MONTAGE = "-";

    /** 拼接符 */
    public static String SLASH = "/";
    /** 请求参数校验失败 */
    public static final String ERROR_CODE_9996 = "9996";
    /**  请求参数为空*/
    public static final String ERROR_CODE_9997 = "9997";
    /**  请求参数解析失败*/
    public static final String ERROR_CODE_9998 = "9998";
    /**  操作失败*/
    public static final String ERROR_CODE_9999 = "9999";
    
    /**  数据库异常*/
    public static final String ERROR_CODE_9995 = "9995";
    
    /**  账单ID不存在*/
    public static final String ERROR_CODE_9994 = "9994";
    
    /**  版本过低*/
    public static final String ERROR_CODE_9988 = "9988";
    
    /**  提示更新*/
    public static final String ERROR_CODE_9989 = "9989";
    
    /** dicCode:数据字典key，数据同步参数配置 */
    public static final String SYNCHRONIZED_CONFIG = "SYNCHRONIZED_CONFIG";

    /** dicCode:数据字典key，客户端参数配置参数配置 */
    public static final String CLIENT_CONFIG = "CLIENT_CONFIG";
    
    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static {
    	ERROR_MESSAGE.put(SUCCESS_CODE, "操作成功");
        ERROR_MESSAGE.put(ERROR_CODE_9996, "请求参数校验失败");
        ERROR_MESSAGE.put(ERROR_CODE_9997, "请求参数为空");
        ERROR_MESSAGE.put(ERROR_CODE_9998, "请求参数解析失败");
        ERROR_MESSAGE.put(ERROR_CODE_9999, "操作失败");
        ERROR_MESSAGE.put(ERROR_CODE_9995, "数据库异常");
        ERROR_MESSAGE.put(ERROR_CODE_9994, "账单ID不存在");
        ERROR_MESSAGE.put(ERROR_CODE_9988, "版本过低");
        ERROR_MESSAGE.put(ERROR_CODE_9989, "提示更新");
    }

	/**
	 * 未被程序捕获异常
	 */
	public static final String UNCAUGHT_EXCEPTION = "javax.servlet.error.exception";

	/*缓存唯一标识*/
	public static final String CACHE_USER_KEY = "CACHE_USER_KEY";
}