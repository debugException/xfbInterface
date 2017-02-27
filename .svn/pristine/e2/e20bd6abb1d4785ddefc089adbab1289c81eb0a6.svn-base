package cn.emagsoftware.utils.net.http;

import cn.emagsoftware.utils.FileUtilities;
import cn.emagsoftware.utils.net.URLManager;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public final class HttpConnectionManager {
    public static final String HEADER_REQUEST_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String HEADER_REQUEST_CONNECTION = "Connection";
    public static final String HEADER_REQUEST_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_REQUEST_ACCEPT_CHARSET = "Accept-Charset";
    public static final String HEADER_REQUEST_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_REQUEST_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_REQUEST_USER_AGENT = "User-Agent";
    public static final String HEADER_REQUEST_COOKIE = "Cookie";
    public static final String HEADER_RESPONSE_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_RESPONSE_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_RESPONSE_LOCATION = "Location";
    public static final String HEADER_RESPONSE_SET_COOKIE = "Set-Cookie";
    public static final int REDIRECT_MAX_COUNT = 10;
    public static final int CMWAP_CHARGEPAGE_MAX_COUNT = 3;
    private static boolean acceptCookie = true;
    private static boolean useConcatURLModeWhenCMWap = false;
    private static boolean ignoreChargePageWhenCMWap = false;


    public static void setAcceptCookie(boolean accept) {
        acceptCookie = accept;
    }

    public static void setUseConcatURLModeWhenCMWap(boolean useConcatURLModeWhenCMWap) {
        useConcatURLModeWhenCMWap = useConcatURLModeWhenCMWap;
    }

    public static void ignoreChargePageWhenCMWap(boolean ignore) {
        ignoreChargePageWhenCMWap = ignore;
    }

    public static HttpResponseResultStream doGetForStream(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders)
            throws IOException {
        HttpURLConnection httpConn = null;
        InputStream input = null;
        try {
            httpConn = openConnection(url, "GET", followRedirects, connOrReadTimeout, 0, 0, requestHeaders, null);
            HttpResponseResultStream result = new HttpResponseResultStream();
            result.setResponseURL(httpConn.getURL());
            int rspCode = httpConn.getResponseCode();
            result.setResponseCode(rspCode);
            result.setResponseHeaders(httpConn.getHeaderFields());
            input = httpConn.getInputStream();
            result.setResultStream(input);
            result.setHttpURLConn(httpConn);
            return result;
        } catch (IOException e) {
            try {
                if (input != null)
                    input.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw e;
        }
    }

    public static HttpResponseResult doGet(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders) throws IOException {
        HttpResponseResultStream result = doGetForStream(url, followRedirects, connOrReadTimeout, requestHeaders);
        result.generateData();
        return result;
    }

    public static HttpResponseResultStream doPostForStream(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders, Map<String, String> postParams, String postParamsEnc)
            throws IOException {
        HttpURLConnection httpConn = null;
        InputStream input = null;
        try {
            if (requestHeaders == null)
                requestHeaders = new HashMap();
            List contentTypes = new ArrayList();
            contentTypes.add("application/x-www-form-urlencoded");
            requestHeaders.put("Content-Type", contentTypes);
            ByteArrayInputStream paramsData = null;
            if (postParams != null) {
                String postParamsStr = URLManager.concatParams(postParams, postParamsEnc);
                paramsData = new ByteArrayInputStream(postParamsStr.getBytes());
            }
            httpConn = openConnection(url, "POST", followRedirects, connOrReadTimeout, 0, 0, requestHeaders, paramsData);
            HttpResponseResultStream result = new HttpResponseResultStream();
            result.setResponseURL(httpConn.getURL());
            int rspCode = httpConn.getResponseCode();
            result.setResponseCode(rspCode);
            result.setResponseHeaders(httpConn.getHeaderFields());
            input = httpConn.getInputStream();
            result.setResultStream(input);
            result.setHttpURLConn(httpConn);
            return result;
        } catch (IOException e) {
            try {
                if (input != null)
                    input.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw e;
        }
    }

    public static HttpResponseResultStream doPostForStream(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders,  String postParams, String postParamsEnc)
            throws IOException {
        HttpURLConnection httpConn = null;
        InputStream input = null;
        try {
            if (requestHeaders == null)
                requestHeaders = new HashMap();
            List contentTypes = new ArrayList();
            contentTypes.add("application/x-www-form-urlencoded");
            requestHeaders.put("Content-Type", contentTypes);
            ByteArrayInputStream paramsData = null;
            if (postParams != null) {
                String postParamsStr = URLManager.concatParams(postParams, postParamsEnc);
                paramsData = new ByteArrayInputStream(postParamsStr.getBytes());
            }
            httpConn = openConnection(url, "POST", followRedirects, connOrReadTimeout, 0, 0, requestHeaders, paramsData);
            HttpResponseResultStream result = new HttpResponseResultStream();
            result.setResponseURL(httpConn.getURL());
            int rspCode = httpConn.getResponseCode();
            result.setResponseCode(rspCode);
            result.setResponseHeaders(httpConn.getHeaderFields());
            input = httpConn.getInputStream();
            result.setResultStream(input);
            result.setHttpURLConn(httpConn);
            return result;
        } catch (IOException e) {
            try {
                if (input != null)
                    input.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw e;
        }
    }


    public static HttpResponseResult doPost(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders, Map<String, String> postParams, String postParamsEnc)
            throws IOException {
        HttpResponseResultStream result = doPostForStream(url, followRedirects, connOrReadTimeout, requestHeaders, postParams, postParamsEnc);
        result.generateData();
        return result;
    }



    public static HttpResponseResult doPost(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders, String postParams, String postParamsEnc)
            throws IOException {
        HttpResponseResultStream result = doPostForStream(url, followRedirects, connOrReadTimeout, requestHeaders, postParams, postParamsEnc);
        result.generateData();
        return result;
    }

    public static HttpResponseResultStream doPostForStream(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders, InputStream postData)
            throws IOException {
        HttpURLConnection httpConn = null;
        InputStream input = null;
        try {
            if (requestHeaders == null)
                requestHeaders = new HashMap();
            if (!requestHeaders.containsKey("Content-Type")) {
                List contentTypes = new ArrayList();
                contentTypes.add("application/octet-stream");
                requestHeaders.put("Content-Type", contentTypes);
            }
            if (postData == null)
                postData = new ByteArrayInputStream(new byte[0]);
            httpConn = openConnection(url, "POST", followRedirects, connOrReadTimeout, 0, 0, requestHeaders, postData);
            HttpResponseResultStream result = new HttpResponseResultStream();
            result.setResponseURL(httpConn.getURL());
            int rspCode = httpConn.getResponseCode();
            result.setResponseCode(rspCode);
            result.setResponseHeaders(httpConn.getHeaderFields());
            input = httpConn.getInputStream();
            result.setResultStream(input);
            result.setHttpURLConn(httpConn);
            return result;
        } catch (IOException e) {
            try {
                if (input != null)
                    input.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw e;
        }
    }

    public static HttpResponseResult doPost(String url, boolean followRedirects, int connOrReadTimeout, Map<String, List<String>> requestHeaders, InputStream postData) throws IOException {
        HttpResponseResultStream result = doPostForStream(url, followRedirects, connOrReadTimeout, requestHeaders, postData);
        result.generateData();
        return result;
    }

    private static HttpURLConnection openConnection(String url, String method, boolean followRedirects, int connOrReadTimeout, int currentRedirectCount, int currentCMWapChargePageCount, Map<String, List<String>> requestHeaders, InputStream postData)
            throws IOException {
        if (currentRedirectCount < 0)
            throw new IllegalArgumentException("current redirect count can not set to below zero");
        if (currentRedirectCount > 10)
            throw new IOException("too many redirect times");
        if (currentCMWapChargePageCount < 0)
            throw new IllegalArgumentException("current cmwap charge page count can not set to below zero");
        if (currentCMWapChargePageCount > 3)
            throw new IOException("too many showing cmwap charge page times");
        URL originalURL = new URL(url);
        URL myURL = originalURL;
        String concatHost = null;
        java.net.Proxy proxy = null;
        HttpURLConnection httpConn = null;
        OutputStream output = null;
        try {
            if ("https".equals(myURL.getProtocol())) {
                SSLContext sslCont = SSLContext.getInstance("TLS");
                sslCont.init(null, new TrustManager[]{new MyX509TrustManager()}, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sslCont.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier(myURL.getHost()));
                if (proxy == null)
                    httpConn = (HttpsURLConnection) myURL.openConnection();
                else {
                    httpConn = (HttpsURLConnection) myURL.openConnection(proxy);
                }
            } else if (proxy == null) {
                httpConn = (HttpURLConnection) myURL.openConnection();
            } else {
                httpConn = (HttpURLConnection) myURL.openConnection(proxy);
            }
            httpConn.setRequestMethod(method);
            HttpURLConnection.setFollowRedirects(false);
            httpConn.setInstanceFollowRedirects(false);
            httpConn.setDoInput(true);
            if (method.equalsIgnoreCase("POST"))
                httpConn.setDoOutput(true);
            else
                httpConn.setDoOutput(false);
            httpConn.setReadTimeout(connOrReadTimeout);
            httpConn.setConnectTimeout(connOrReadTimeout);
            if (concatHost != null) {
                httpConn.addRequestProperty("X-Online-Host", concatHost);
            }
            boolean cookieFromUser = false;
            if (requestHeaders != null) {
                Iterator entries = requestHeaders.entrySet().iterator();
                Iterator localIterator1;
                for (; entries.hasNext(); localIterator1.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    String key = (String) entry.getKey();
                    List values = (List) entry.getValue();
                    if (key.equalsIgnoreCase("Cookie"))
                        cookieFromUser = true;
                    localIterator1 = values.iterator();
                    String value = (String) localIterator1.next();
                    httpConn.addRequestProperty(key, value);
                }
            }
            if ((method.equalsIgnoreCase("POST")) && (postData != null)) {
                output = httpConn.getOutputStream();
                FileUtilities.readAndWrite(postData, output, 2048);
                output.close();
            }
            int rspCode = httpConn.getResponseCode();
            if ((rspCode == 301) || (rspCode == 302) || (rspCode == 303)) {
                if (!followRedirects) {
                    return httpConn;
                }
                String location = httpConn.getHeaderField("Location");
                if (location == null)
                    throw new IOException("redirects failed:could not find the location header");
                if (location.toLowerCase().indexOf(originalURL.getProtocol() + ":") < 0)
                    location = originalURL.getProtocol() + ":" + originalURL.getHost() + location;
                httpConn.disconnect();
                return openConnection(location, "GET", followRedirects, connOrReadTimeout, ++currentRedirectCount, currentCMWapChargePageCount, requestHeaders, null);
            }
            if (rspCode >= 400) {
                throw new IOException("requesting returns error http code:" + rspCode);
            }

            if (((concatHost != null) || (proxy != null)) && (!ignoreChargePageWhenCMWap)) {
                String contentType = httpConn.getHeaderField("Content-Type");
                if ((contentType != null) && (contentType.indexOf("vnd.wap.wml") != -1)) {
                    InputStream input = null;
                    try {
                        input = httpConn.getInputStream();
                        BufferedInputStream buffInput = new BufferedInputStream(input);
                        ByteArrayOutputStream tempOutput = new ByteArrayOutputStream();
                        byte[] b = new byte[2048];
                        int len;
                        while ((len = buffInput.read(b)) > 0) {
                            tempOutput.write(b, 0, len);
                        }
                        String wmlStr = new String(tempOutput.toByteArray(), "UTF-8");

                        String parseURL = null;
                        try {
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser xmlParser = factory.newPullParser();
                            xmlParser.setInput(new StringReader(wmlStr));
                            boolean onEnterForward = false;
                            int eventType = xmlParser.getEventType();
                            while (eventType != 1) {
                                switch (eventType) {
                                    case 2:
                                        String tagName = xmlParser.getName().toLowerCase();
                                        if ("onevent".equals(tagName)) {
                                            String s = xmlParser.getAttributeValue(null, "type").toLowerCase();
                                            if ("onenterforward".equals(s))
                                                onEnterForward = true;
                                        } else if ("go".equals(tagName)) {
                                            if (onEnterForward)
                                                parseURL = xmlParser.getAttributeValue(null, "href");
                                        }
                                        break;
                                }
                                if (parseURL != null)
                                    break;
                                eventType = xmlParser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if ((parseURL == null) || (parseURL.equals(""))) {
                            parseURL = url;
                        }
                        return openConnection(parseURL, method, followRedirects, connOrReadTimeout, currentRedirectCount, ++currentCMWapChargePageCount, requestHeaders, postData);
                    } finally {
                        try {
                            if (input != null)
                                input.close();
                        } finally {
                            httpConn.disconnect();
                        }
                    }
                }
            }
            return httpConn;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (output != null)
                    output.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw e;
        } catch (Exception e) {
            try {
                if (output != null)
                    output.close();
            } finally {
                if (httpConn != null)
                    httpConn.disconnect();
            }
            throw new RuntimeException(e);
        }
    }


    private static class MyHostnameVerifier
            implements HostnameVerifier {
        private String hostname;

        public MyHostnameVerifier(String hostname) {
            this.hostname = hostname;
        }

        public boolean verify(String hostname, SSLSession session) {
            if (this.hostname.equals(hostname))
                return true;
            return false;
        }
    }

    private static class MyX509TrustManager
            implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}

