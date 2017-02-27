package cn.emagsoftware.utils;

import cn.emagsoftware.utils.json.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONStringer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * App Utils
 *
 * @version 1.0.0
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class AppUtils {
    private static JsonValueProcessor jsonValueProcessor = new JsonDateValueProcessor(
            "yyyyMMddHHmmss");

    /**
     * 把name=value;name=value形式的字符串分割成map
     *
     * @param value 用;分割的键值对
     * @return Map
     */
    public static Map<String, String> splitPairValues(String value) {
        if (StringUtils.isBlank(value))
            return Collections.emptyMap();
        String[] pairs = StringUtils.split(value, ';');
        Map<String, String> map = new CaseInsensitiveMap(pairs.length);
        for (String pair : pairs) {
            if (StringUtils.isBlank(pair))
                continue;
            String[] keyValue = StringUtils.split(pair, '=');
            if (keyValue.length < 2) {
                map.put(keyValue[0], null);
            } else {
                map.put(keyValue[0], keyValue[1]);
            }

        }
        return map;
    }

    /**
     * 从request中获取所有参数按照参数名称升序排列生成字符串格式为name1+value1+name2+value2+...
     *
     * @param request  Http 请求对象
     * @param excludes 排除参数名
     * @return
     */
    public static String createRequestSignRawValue(HttpServletRequest request,
                                                   Collection excludes) {
        StringBuilder rawValue = new StringBuilder();
        Enumeration<String> paramNames = request.getParameterNames();
        List<String> names = new ArrayList<String>();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            if (excludes != null && excludes.contains(name))
                continue;
            names.add(name);
        }
        Collections.sort(names);
        for (String name : names) {
            String valueStr;
            String[] values = request.getParameterValues(name);
            if (values == null || values.length == 0) {
                valueStr = "";
            } else if (values.length == 1) {
                valueStr = values[0];
            } else {
                Arrays.sort(values);
                valueStr = StringUtils.join(values);
            }
            rawValue.append(name).append(valueStr);
        }
        return rawValue.toString();
    }

    public static String joinURL(String serverUrl, String relativePath) {
        if (serverUrl.endsWith("/"))
            serverUrl = serverUrl.substring(0, serverUrl.length() - 1);
        if (relativePath.contains("\\"))
            relativePath = StringUtils.replace(relativePath, "\\", "/");
        if (relativePath.startsWith("/"))
            relativePath = relativePath.substring(1);
        return serverUrl.concat("/").concat(relativePath);
    }

    public static String md5Hex(byte[] bytes) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] result = md5.digest(bytes);
            StringBuilder stringBuilder = new StringBuilder(result.length);
            for (byte b : result) {
                stringBuilder.append(Integer.toHexString(b & 0xff));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("md5 exception ", e);
        }
    }

    /**
     * 获取客户端最新版本号
     *
     * @param versions
     * @return
     */
    public static String getNewestVersion(Collection versions) {
        if (CollectionUtils.isEmpty(versions))
            return null;
        List<String> clientVersions = (List<String>) versions;
        Collections.sort(clientVersions);
        return clientVersions.get(clientVersions.size() - 1);
    }

    /**
     * 客户端版本号比较
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (StringUtils.isEmpty(version1))
            return 0;
        if (StringUtils.isEmpty(version2))
            return 0;
        String[] value1 = version1.split("\\.");
        String[] value2 = version2.split("\\.");
        int n = Math.min(value1.length, value2.length);
        for (int i = 0; i < n; i++) {
            if (!StringUtils.isNumeric(value1[i])
                    || !StringUtils.isNumeric(value2[i]))
                throw new IllegalArgumentException(
                        "version is illegal, version1:" + value1[i]
                                + ",version2:" + value2[i]);

            if (Integer.parseInt(value1[i]) < Integer.parseInt(value2[i]))
                return -1;
            if (Integer.parseInt(value1[i]) > Integer.parseInt(value2[i]))
                return 1;
        }
        if (value1.length != value2.length)
            return (value1.length > value2.length) ? 1 : -1;
        return 0;
    }

    public static String toJSONString(String objectName, Object object,
                                      String[] excludes) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        config.setExcludes(excludes);
        return toJSONString(objectName, object, config);
    }

    public static String toJSONString(String objectName, Object object) {
        return toJSONString(objectName, object, (JsonConfig) null);
    }

    public static String toJSONStringIncludes(Object object, String[] includes) {
        JsonConfig config = setIncludes(object.getClass(), includes);
        return toJSONString(object, config);
    }

    public static String toJSONString(Object object, String[] excludes) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, jsonValueProcessor);
        config.setExcludes(excludes);
        return toJSONString(object, config);
    }

    public static String toJSONString(Object object) {
        return toJSONString(object, (JsonConfig) null);
    }

    public static String toJSONString(Object object, JsonConfig config) {
        JSONObject jsonObject;
        if (config != null)
            jsonObject = JSONObject.fromObject(object, config);
        else
            jsonObject = JSONObject.fromObject(object);

        return jsonObject.toString();
    }

    public static String toJSONString(String objectName, Object object,
                                      JsonConfig config) {
        JSONObject jsonObject;
        if (config != null)
            jsonObject = JSONObject.fromObject(object, config);
        else
            jsonObject = JSONObject.fromObject(object);
        return new JSONStringer().object().key(objectName).value(jsonObject)
                .endObject().toString();
    }

    public static String toJSONArrayString(String objectName, Object object,
                                           JsonConfig config) {
        JSONArray json;
        if (config != null)
            json = JSONArray.fromObject(object, config);
        else
            json = JSONArray.fromObject(object);
        return new JSONStringer().object().key(objectName).value(json)
                .endObject().toString();
    }

    public static String toJSONArrayStringExcludes(String objectName,
                                                   Object object, String[] excludes) {
        JsonConfig config = new JsonConfig();
        config.setExcludes(excludes);
        return toJSONArrayString(objectName, object, config);
    }

    public static String toJSONArrayStringIncludes(String objectName,
                                                   Object object, String[] includes) {
        JsonConfig config = setIncludes(object.getClass(), includes);
        return toJSONArrayString(objectName, object, config);
    }

    public static String toJSONStringIncludes(String objectName, Object object,
                                              String[] includes) {
        JsonConfig config = setIncludes(object.getClass(), includes);
        return toJSONString(objectName, object, config);
    }

    public static JsonConfig setIncludes(Class c, String[] includes) {
        JsonConfig config = new JsonConfig();
        Set<String> set = new HashSet<String>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            set.add(field.getName());
        }
        if (includes == null)
            return null;
        for (String string : includes) {
            set.remove(string);
        }
        String[] excludes = new String[set.size()];
        config.setExcludes(set.toArray(excludes));
        return config;
    }

    public static String formatPhotoNumber(String photoNumber) {
        if (StringUtils.isEmpty(photoNumber))
            return null;

        if (photoNumber.startsWith("86"))
            return photoNumber.substring(2, photoNumber.length());

        if (photoNumber.startsWith("+86"))
            return photoNumber.substring(3, photoNumber.length());

        return photoNumber;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        // 去掉"-"符号
        String uuidStr = uuid.toString().replaceAll("-", "");
        return uuidStr;
    }
}
