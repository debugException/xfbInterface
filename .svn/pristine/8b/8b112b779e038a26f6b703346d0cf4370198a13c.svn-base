package cn.emagsoftware.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public final class Utilities {
    /**
     * 懒加载每次加载的个数
     */

    public static final int LAZYLOADING_EACH_COUNT = 10;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private Utilities() {
    }


    public static String md5Request(String uri, String timestamp, String requestRawValue, String clientKey, String appKey) {
        StringBuilder builder = new StringBuilder(256);
        builder.append(uri).append(timestamp).append(requestRawValue).append(clientKey);
        if (appKey != null)
            builder.append(appKey);
        return md5Hex(builder.toString());
    }

    public static byte[] md5(String src) {
        try {
            return md5(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] md5(byte[] src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5Hex(String src) {
        return bytesToHex(md5(src));
    }

    public static String bytesToHex(byte[] b) {
        int length = (int) (b.length * 1.5);
        StringBuilder str = new StringBuilder(length);
        for (int i = 0; i < b.length; i++) {
            str.append(byteToHexString(b[i]));
        }
        return str.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b & 0xff;
        int d1, d2;
        if (n > 0xf) {
            d1 = n >> 4;
            d2 = n & 0x0f;
        } else {
            d1 = 0;
            d2 = n;
        }
        return String.valueOf(new char[]{HEX_DIGITS[d1], HEX_DIGITS[d2]});
    }


    /**
     * 转换当前时间
     *
     * @param date
     * @return
     */
    public static String getCurrentTime(long date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(new Date(date));
        return str;
    }

    /**
     * 排序拼接参数
     *
     * @param hashMaps
     * @return
     */
    public static String createRequestSignRawValue(Map<String, String> hashMaps) {
        if (hashMaps != null) {
            StringBuilder rawValue = new StringBuilder();
            Set<String> nameSets = hashMaps.keySet();
            Object[] names = nameSets.toArray();
            Arrays.sort(names);
            // Collection<String> valueSets = hashMaps.values();
            // Object[] values = valueSets.toArray();
            // Arrays.sort(values);
            for (int i = 0; i < names.length; i++) {
                String name = String.valueOf(names[i]);
                rawValue.append(name).append(hashMaps.get(names[i]));
            }
            return rawValue.toString();
        } else {
            return "";
        }
    }

    /**
     * 截取相对路径地址方法
     *
     * @param URL_GENERIC,str
     * @return
     */
    public static String changeUrl(String URL_GENERIC,String str) {
        int start = str.indexOf(URL_GENERIC);
        String newUrl = str.substring(start + URL_GENERIC.length());
        return newUrl;
    }

}
