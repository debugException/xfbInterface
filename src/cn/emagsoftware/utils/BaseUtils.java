package cn.emagsoftware.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class BaseUtils {



    /**
     * 构造函数
     */
    private BaseUtils() {
    }

    /**
     * 获取系统换行符
     *
     * @return 换行符
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * 判断是数字
     *
     * @param param
     *            参数
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNum(String param) {
        if (null != param && param.matches("\\d+")) {
            return true;
        }
        return false;
    }

    /**
     * 判断是数字（有小数位）
     *
     * @param param
     *            参数
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNumFloat(String param) {
        if (null != param && (param.matches("[0-9]+.[0-9]+") || param.matches("[0-9]+"))) {
            return true;
        }
        return false;
    }

    /**
     * 判断数字是1~10
     *
     * @param param
     *            参数
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean isOneToTen(String param) {
        if (null != param && param.matches("[1-9]") || param.matches("10")) {
            return true;
        }
        return false;
    }

    /**
     * 判断是Email
     *
     * @param email
     *            Email
     * @return boolean
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            String regu = "(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int|name|asia)$";
            if (email.matches(regu)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前时间
     *
     * @param style
     *            格式
     * @return String 当前时间
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentDate(String style) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        // 获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取客户端IP
     *
     * @param request
     *            HttpServletRequest
     * @return 客户端IP
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取CMWAP网关IP
     *
     * @param request
     * @return CMWAP网关IP
     */
    public static String getCmWapIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    /**
     * 获取日志Key
     *
     * @return 日志Key
     */
    public synchronized static long getLogId() {
        long time = System.currentTimeMillis();
        Random rdm = new Random();
        return time + rdm.nextInt(100);
    }

    /**
     * 把NULL值转化为空("")
     *
     * @param strValue
     *            String 传入一个字符串
     * @return String strValue=null,return"";str=str
     */

    public static String getString(String strValue) {
        try {
            if (strValue == null) {
                return "";
            }
            return strValue;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }



}