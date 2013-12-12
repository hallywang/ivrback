package com.emag.url;

import com.vivame.util.StringUtil;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by IntelliJ IDEA.
 * User: hp
 * Date: 2010-4-23
 * Time: 17:09:48
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtil {
    private static final Logger logger = Logger.getLogger(HttpUtil.class.getName());

    private HttpClient httpClient;

    public HttpUtil() {
        httpClient = new HttpClient();
    }

    /**
     * 向一个http地址发送一个get请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendGetNotEncode(String uri, Map<String, String> paramsMap, Map<String, String> headersMap) {
        //拼接请求参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && uri.indexOf("?") < 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }

                param.append(entry.getKey()).append("=").append(entry.getValue());

                i++;
            }
            uri += param.toString();
        }
        GetMethod method = new GetMethod(uri);

        //向请求中加入头部信息
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
    public HashMap getResponseContentType(String uri, Map<String, String> paramsMap, Map<String, String> headersMap) {
        //拼接请求参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && uri.indexOf("?") < 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }

                //读参数进行urlEncode
                try {
                    param.append(entry.getKey()).append("=").append(URLEncoder.encode(StringUtil.getString(entry.getValue()), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                i++;
            }
            uri += param.toString();
        }
        GetMethod method = new GetMethod(uri);

        //向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        HashMap map = new HashMap();
        String responseContent = sendMethod(method);
        Header header = method.getResponseHeader("Content-Type");
        String contentType = header.getValue();
        map.put("responseContent", responseContent);
        map.put("contentType", contentType);

        return map;
    }

    /**
     * 向一个http地址发送一个get请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendGet(String uri, Map<String, String> paramsMap, Map<String, String> headersMap) {
        //拼接请求参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && uri.indexOf("?") < 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }

                //读参数进行urlEncode
                try {
                    param.append(entry.getKey()).append("=").append(URLEncoder.encode(StringUtil.getString(entry.getValue()), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                i++;
            }
            uri += param.toString();
        }
        GetMethod method = new GetMethod(uri);

        //向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        return sendMethod(method);
    }

    /**
     * 向一个http地址发送一个post请求
     *
     * @param uri        http地址
     * @param paramsMap  参数信息
     * @param headersMap 头部信息
     * @return 返回相应内容
     */
    public String sendPost(String uri, Map<String, String> paramsMap, Map<String, String> headersMap) {
        PostMethod method = new PostMethod(uri);

        //向请求中加入需要post过去的数据
        if (paramsMap != null && !paramsMap.isEmpty()) {
            NameValuePair[] data = new NameValuePair[]{};
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                NameValuePair tmpPair = new NameValuePair(entry.getKey(), entry.getValue());
                ArrayUtils.add(data, tmpPair);
            }
            method.setRequestBody(data);  //此处有待确定
        }

        //向请求中加入头部信息
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                method.addRequestHeader(entry.getKey(), entry.getValue());
            }
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
        String rtnStr = "";

        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.info("Method failed: " + method.getStatusLine());
            }
            rtnStr = method.getResponseBodyAsString();
        } catch (HttpException e) {
            logger.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
        } catch (IOException e) {
            logger.error("发生网络异常", e);
        } finally {
            // 释放连接
            method.releaseConnection();
        }

        return rtnStr;
    }

    public void setConnectionTimeout(int timeout) {
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
    }

    public void setReadTimeout(int timeout) {
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
    }
}
