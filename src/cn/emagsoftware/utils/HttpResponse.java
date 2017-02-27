/*
 * @(#)HttpResponse.java 1.0.0 14/02/26
 * Copyright 2014© Emagsoftware Technology Co., Ltd. All Rights reserved.
 */

package cn.emagsoftware.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * function description
 */
public class HttpResponse {
    private Integer statusCode = null;
    private Map<String, String> headers = null;
    private String body;
    private String contentType;
    private byte[] buffer;

    public HttpResponse setHeader(String name, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(name, value);
        return this;
    }

    public String getHeader(String name) {
        return headers == null ? null : headers.get(name);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
