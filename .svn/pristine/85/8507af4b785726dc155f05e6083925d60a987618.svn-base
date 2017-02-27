/* StringUtil - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package cn.emagsoftware.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * 工具类
 *
 * @version 1.0.0
 */
public class CipherUtils {
    public static final char[] HEX_DIGITS
            = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F'};

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

    public static String md532(String str) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes("UTF-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
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


    public static String hmacSHA256(String key, String value) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("UTF-8");
            byte[] dataBytes = value.getBytes("UTF-8");
            SecretKey secret = new SecretKeySpec(secretByte, "RAW");
            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);
//            byte[] hexB = new Hex().encode(doFinal);
            return Base64.encodeBase64String(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hmacSHA256(String key, byte[] dataBytes) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("UTF-8");
            SecretKey secret = new SecretKeySpec(secretByte, "RAW");
            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);
//            byte[] hexB = new Hex().encode(doFinal);
            return Base64.encodeBase64String(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = HEX_DIGITS[(bt & 0xf0) >> 4];
        char c1 = HEX_DIGITS[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    public static String getFileMD5String(MultipartFile file) throws IOException {
        FileInputStream in = (FileInputStream) file.getInputStream();
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.getSize());
        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException("getFileMD5String error: ", e);
        }
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }
}
