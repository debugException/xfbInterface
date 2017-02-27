package cn.emagsoftware.xfb.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yindongyong
 * Date: 15-4-8
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class SysUserConstant {
//   成功
    public  static  String ERROR_CODE_1000 = "0";
//  已存在的用户手机号码
    public  static  String ERROR_CODE_1001 = "1001";
//  身份证号码不合法
    public  static  String ERROR_CODE_1002 = "1002";
//  短信验证码超时
    public  static  String ERROR_CODE_1003 = "1003";
//  短信验证码错误
    public  static  String ERROR_CODE_1004 = "1004";
//  登录密码不正确
    public  static  String ERROR_CODE_1005 = "1005";
//  推荐人不存在
    public  static  String ERROR_CODE_1006 = "1006";
//  当前密码不正确
    public  static  String ERROR_CODE_1008 = "1008";
//    新密码与原密码不能相同
    public  static  String ERROR_CODE_1009 = "1009";

    public  static  String ERROR_CODE_2000 = "2000";
//十分钟短信有效期 单位：秒
    public static  int   SMS_VALID_TIME = 10*60;

    public static int USER_MIN_AGE = 18;

    public static int USER_MAX_AGE = 60;


    private static String smsHeader = "sname=dlxinfb&spwd=50wTKJIW&scorpid=&sprdid=1012818&sdst=";

    private static String smsBody = "&smsg=";

    private static String smsEnd = "【信分宝】";

    private static String smsUrl = "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit";

    public static Hashtable CARD_PROVINCE = new Hashtable();

    public static Map<String,String> SMSCODE_MAP = new HashMap<String,String>();

    public static Map<String,Object> LOGIN_MAP = new HashMap<String,Object>();

    /** 返回码MAP */
    public static final Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
    static {
        ERROR_MESSAGE.put(ERROR_CODE_1000, "成功");
        ERROR_MESSAGE.put(ERROR_CODE_1001, "已注册的用户");
        ERROR_MESSAGE.put(ERROR_CODE_1002, "身份证号码不合法");
        ERROR_MESSAGE.put(ERROR_CODE_1003, "短信验证码超时");
        ERROR_MESSAGE.put(ERROR_CODE_1004, " 短信验证码错误");
        ERROR_MESSAGE.put(ERROR_CODE_1005, "登录密码不正确");
        ERROR_MESSAGE.put(ERROR_CODE_1006, "推荐人不存在");
        ERROR_MESSAGE.put(ERROR_CODE_2000, "其它异常");
        ERROR_MESSAGE.put(ERROR_CODE_1008, "当前密码不正确");
        ERROR_MESSAGE.put(ERROR_CODE_1009, "新密码与原密码不能相同");

        CARD_PROVINCE.put("11", "北京");
        CARD_PROVINCE.put("12", "天津");
        CARD_PROVINCE.put("13", "河北");
        CARD_PROVINCE.put("14", "山西");
        CARD_PROVINCE.put("15", "内蒙古");
        CARD_PROVINCE.put("21", "辽宁");
        CARD_PROVINCE.put("22", "吉林");
        CARD_PROVINCE.put("23", "黑龙江");
        CARD_PROVINCE.put("31", "上海");
        CARD_PROVINCE.put("32", "江苏");
        CARD_PROVINCE.put("33", "浙江");
        CARD_PROVINCE.put("34", "安徽");
        CARD_PROVINCE.put("35", "福建");
        CARD_PROVINCE.put("36", "江西");
        CARD_PROVINCE.put("37", "山东");
        CARD_PROVINCE.put("41", "河南");
        CARD_PROVINCE.put("42", "湖北");
        CARD_PROVINCE.put("43", "湖南");
        CARD_PROVINCE.put("44", "广东");
        CARD_PROVINCE.put("45", "广西");
        CARD_PROVINCE.put("46", "海南");
        CARD_PROVINCE.put("50", "重庆");
        CARD_PROVINCE.put("51", "四川");
        CARD_PROVINCE.put("52", "贵州");
        CARD_PROVINCE.put("53", "云南");
        CARD_PROVINCE.put("54", "西藏");
        CARD_PROVINCE.put("61", "陕西");
        CARD_PROVINCE.put("62", "甘肃");
        CARD_PROVINCE.put("63", "青海");
        CARD_PROVINCE.put("64", "宁夏");
        CARD_PROVINCE.put("65", "新疆");
        CARD_PROVINCE.put("71", "台湾");
        CARD_PROVINCE.put("81", "香港");
        CARD_PROVINCE.put("82", "澳门");
        CARD_PROVINCE.put("91", "国外");
    }


    /**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }


    public static String SMS(String postData,String userPhone) throws Exception {
        URL url = null;
        HttpURLConnection conn= null;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        try {
            //发送POST请求
            String sendData = smsHeader+userPhone+smsBody+java.net.URLEncoder.encode(postData+smsEnd,"utf-8");

            url = new URL(smsUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + sendData.length());
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(sendData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }finally {
            try{
                if (in!=null){
                    in.close();
                }
                if(out!=null){
                    out.close();
                }
            }catch (Exception ex){
                throw  ex;
            }

        }
        return "";
    }

}
