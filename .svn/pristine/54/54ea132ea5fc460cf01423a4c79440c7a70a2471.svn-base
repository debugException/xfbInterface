package cn.tyiti.xfb.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: yindongyong
 * Date: 15-4-8
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class SmsUtil {

//    private static String smsHeader = "sname=dlxinfb&spwd=50wTKJIW&scorpid=&sprdid=1012818&sdst=";
    private static String smsHeader = "sname=dlxinfb0&spwd=q6WtgwF4&scorpid=&sprdid=1012818&sdst=";
    private static String smsBody = "&smsg=";

    private static String smsEnd = "【信分宝】";

    private static String smsUrl = "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit";

    /**
     *  短信发送
     * @param postData
     * @param userPhone
     * @return
     * @throws Exception
     */
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
