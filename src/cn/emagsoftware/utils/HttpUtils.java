package cn.emagsoftware.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 处理客户端HTTP调用方法.
 * <p/>
 * <p/>
 * <pre>
 *
 * //发送Get请求
 * HttpUtils h = new HttpUtils();
 *
 * //组装消息头
 * Map&lt;String, String&gt; head = new HashMap&lt;String,String&gt;();
 * head.put(&quot;content&quot;, &quot;text/html;charset=utf-8&quot;);
 *
 * //组装消息体
 * Map&lt;String, String&gt; param = new HashMap&lt;String, String&gt;();
 * param.put(&quot;userId&quot;, &quot;123&quot;);
 * param.put(&quot;age&quot;,&quot;20&quot;);
 * param.put(&quot;userName&quot;,&quot;Tom&quot;);
 * param.put(&quot;userAge&quot;,&quot;18&quot;);
 *
 * //发送消息
 * String re = h.sendGet(&quot;http://localhost:8090/EmagInterfaceFrame/sample/sample22.do&quot;, param, head);
 *
 * -----------------------------------------------------------
 *
 * //发送Post请求
 * HttpUtils h = new HttpUtils();
 *
 * //组装消息头
 * Map&lt;String, String&gt; head = new HashMap&lt;String,String&gt;();
 * head.put(&quot;content&quot;, &quot;text/html;charset=utf-8&quot;);
 *
 * //组装消息体
 * Map&lt;String, String&gt; param = new HashMap&lt;String, String&gt;();
 * param.put(&quot;userId&quot;, &quot;1234&quot;);
 * param.put(&quot;age&quot;,&quot;21&quot;);
 * param.put(&quot;userName&quot;,&quot;Jack&quot;);
 * param.put(&quot;userAge&quot;,&quot;20&quot;);
 *
 * //发送消息
 * String re = h.sendGet(&quot;http://localhost:8090/EmagInterfaceFrame/sample/sample22.do&quot;, param, head);
 *
 * -----------------------------------------------------------
 *
 * //发送XML报文的Post请求
 * HttpUtils h = new HttpUtils();
 *
 * //XML格式报文
 * String xml=&quot;...&quot;;
 *
 * //发送报文
 * String re = h.sendPostXml(&quot;http://ip:port/.../...&quot;,xml);
 *
 * </pre>
 */
public class HttpUtils {

    private static final Logger logger = Logger.getLogger(HttpUtils.class
            .getName());

    private final HttpClient httpClient;

    /**
     * 构造函数，设置默认超时时间
     */
    public HttpUtils() {
        httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(5000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(21000);
    }

    /**
     * 向一个http地址发送一个get请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendGetNotEncode(String uri, Map<String, String> paramsMap,
                                   Map<String, String> headersMap) {
        // 拼接请求参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && uri.indexOf("?") < 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }

                param.append(entry.getKey()).append("=")
                        .append(entry.getValue());

                i++;
            }
            uri += param.toString();
        }
        GetMethod method = new GetMethod(uri);

        // 向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        return sendMethod(method);
    }

    /**
     * 向一个http地址发送一个get请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendGet(String uri, Map<String, String> paramsMap,
                          Map<String, String> headersMap) {
        // 拼接请求参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && uri.indexOf("?") < 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }

                // 读参数进行urlEncode
                try {
                    param.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                i++;
            }
            uri += param.toString();
        }
        GetMethod method = new GetMethod(uri);

        // 向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        return sendMethod(method);
    }

    /**
     * 向一个http地址发送一个post xml请求
     *
     * @param uri http地址
     * @param xml 请求报文
     * @return 返回相应内容
     */
    public String sendPostXml(String uri, String xml) {
        PostMethod method = new PostMethod(uri);
        method.setRequestHeader("Connection", "close");
        try {
            method.setRequestEntity(new StringRequestEntity(xml,
                    "text/xml; charset=" + "UTF-8", null));
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码格式", e);
        }
        return sendMethod(method);
    }

    /**
     * 发送http请求
     *
     * @param method HttpMethodBase
     * @return 返回相应内容
     */
    private String sendMethod(HttpMethodBase method) {
        logger.info("发送HTTP POST请求");
        StringBuilder sb = new StringBuilder();
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.info("响应状态异常[" + statusCode + "]："
                        + method.getStatusLine());
            } else {
                logger.info("响应状态正常[" + statusCode + "]");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream()));
                String str = Constant.EMPTY;
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
            }
        } catch (HttpException e) {
            logger.error("HTTP请求异常", e);
        } catch (IOException e) {
            logger.error("输入输出流异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
        }
        logger.info("对方响应报文：" + sb.toString());
        return sb.toString();
    }

    /**
     * 普通POST请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendPost(String uri, Map<String, String> headersMap,
                           Map<String, String> paramsMap) {
        PostMethod postMethod = new PostMethod(uri);
        postMethod.getParams().setContentCharset(Constant.CH_ENCODING_UTF8);
        postMethod.setRequestHeader(Constant.HTTP_CONTENT_TYPE,
                Constant.HTTP_APPLICATION_FORM_URLENCODED);

        // 向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            logger.info("填充消息头：" + headersMap.size());
            for (Entry<String, String> entry : headersMap.entrySet()) {
                postMethod.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        // 向请求中加入需要post过去的数据
        if (paramsMap != null && !paramsMap.isEmpty()) {
            logger.info("填充消息体：" + paramsMap.size());
            NameValuePair[] data = new NameValuePair[paramsMap.size()];
            int index = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                data[index] = new NameValuePair(entry.getKey(),
                        entry.getValue());
                index++;
            }
            postMethod.setRequestBody(data);
        }

        return sendMethod(postMethod);
    }

    /**
     * 支持附件的POST请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @param fileMap    文件信息
     * @return 返回相应内容
     */
    public String sendPostByMultipartFormData(String uri,
                                              Map<String, String> headersMap, Map<String, String> paramsMap,
                                              Map<String, File> fileMap) {
        PostMethod postMethod = new PostMethod(uri);
        postMethod.getParams().setContentCharset(Constant.CH_ENCODING_UTF8);

        // 头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            logger.info("填充消息头：" + headersMap.size());
            for (Entry<String, String> entry : headersMap.entrySet()) {
                // 必须去除换行，测试中出现丢失结尾一个单元。
                postMethod.addRequestHeader(new Header(entry.getKey(),
                        CommonUtils.getLineStr(entry.getValue())));
            }
        }

        StringPart[] sp = new StringPart[0];
        // 主体信息
        if (paramsMap != null && !paramsMap.isEmpty()) {
            logger.info("填充消息体：" + paramsMap.size());
            sp = new StringPart[paramsMap.size()];
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                sp[i] = new StringPart(entry.getKey(), entry.getValue(),
                        Constant.CH_ENCODING_UTF8);
                i++;
            }
        }

        // 文件信息
        FilePart[] fp = new FilePart[0];
        if (fileMap != null && !fileMap.isEmpty()) {
            logger.info("填充文件信息：" + fileMap.size());
            fp = new FilePart[fileMap.size()];
            int i = 0;
            for (Entry<String, File> entry : fileMap.entrySet()) {
                try {
                    FilePart f = new FilePart(entry.getKey(), entry.getValue()
                            .getName(), entry.getValue(), null,
                            Constant.CH_ENCODING_UTF8);
                    fp[i] = f;
                } catch (Exception e) {
                    logger.error("上传的文件未找到", e);
                }
                i++;
            }
        }

        // 组装参数
        MultipartRequestEntity mrp = null;
        if (fp.length != 0) {
            Part[] p = new Part[sp.length + fp.length];
            for (int i = 0; i < sp.length; i++) {
                p[i] = sp[i];
            }
            for (int i = 0; i < fp.length; i++) {
                p[i + sp.length] = fp[i];
            }
            mrp = new MultipartRequestEntity(p, postMethod.getParams());
        } else {
            mrp = new MultipartRequestEntity(sp, postMethod.getParams());
        }

        postMethod.setRequestEntity(mrp);
        postMethod.setRequestHeader(Constant.HTTP_CONTENT_TYPE,
                mrp.getContentType());

        return sendMethod(postMethod);
    }

}
