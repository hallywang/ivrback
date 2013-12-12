package com.emag.url;

import com.vivame.util.StringUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;


public class HttpRequestWithXml {

    private String defaultContentEncoding;

    public HttpRequestWithXml() {
        this.defaultContentEncoding = Charset.defaultCharset().name();
    }


    public HttpRespons sendGet(String urlString, String xmlContent) throws IOException {
        return this.send(urlString, xmlContent, "GET", null, null);
    }


    public HttpRespons sendGet(String urlString, String xmlContent, Map<String, String> params)
            throws IOException {
        return this.send(urlString, xmlContent, "GET", params, null);
    }


    public HttpRespons sendGet(String urlString, String xmlContent, Map<String, String> params,
                               Map<String, String> propertys) throws IOException {
        return this.send(urlString, xmlContent, "GET", params, propertys);
    }


    public HttpRespons sendPost(String urlString, String xmlContent) throws IOException {
        return this.send(urlString, xmlContent, "POST", null, null);
    }


    public HttpRespons sendPost(String urlString, String xmlContent, Map<String, String> params)
            throws IOException {
        return this.send(urlString, xmlContent, "POST", params, null);
    }


    public HttpRespons sendPost(String urlString, String xmlContent, Map<String, String> params,
                                Map<String, String> propertys) throws IOException {
        return this.send(urlString, xmlContent, "POST", params, propertys);
    }



    private HttpRespons send(String urlString, String xmlContent, String method,
                             Map<String, String> parameters, Map<String, String> propertys)
            throws IOException {
        HttpURLConnection urlConnection = null;

        //get请求时，直接参数拼接到url地址后面
        if ("GET".equals(method) && parameters != null) {
            StringBuffer param = new StringBuffer();
            int i = 0;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
               if (i == 0) {
                    param.append("?");
                } else {
                    param.append("&");
                }
                param.append(entry.getKey()).append("=").append(entry.getValue());
                i++;
            }
            urlString += param;
        }
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(7000);

        if (propertys != null) {
            for (String key : propertys.keySet()) {
                urlConnection.addRequestProperty(key, propertys.get(key));
            }
        }

        boolean hasWriteFlag = false;
        if (method.equalsIgnoreCase("POST") && parameters != null) {
            StringBuffer param = new StringBuffer();
            for (String key : parameters.keySet()) {
                param.append("&");
                param.append(key).append("=").append(parameters.get(key));
            }
            urlConnection.getOutputStream().write(param.toString().getBytes());
            hasWriteFlag = true;
        }

        if (!StringUtil.isEmpty(xmlContent)) {
            urlConnection.getOutputStream().write(xmlContent.getBytes());
            hasWriteFlag = true;
        }

        if (hasWriteFlag) {
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();
        }

        return this.makeContent(urlString, urlConnection);
    }


    private HttpRespons makeContent(String urlString,
                                    HttpURLConnection urlConnection) throws IOException {
        HttpRespons httpResponser = new HttpRespons();
        try {
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(in));
            httpResponser.contentCollection = new Vector<String>();
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                httpResponser.contentCollection.add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            String ecod = urlConnection.getContentEncoding();
            if (ecod == null) {
                ecod = this.defaultContentEncoding;
            }

            httpResponser.urlString = urlString;

            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
            httpResponser.file = urlConnection.getURL().getFile();
            httpResponser.host = urlConnection.getURL().getHost();
            httpResponser.path = urlConnection.getURL().getPath();
            httpResponser.port = urlConnection.getURL().getPort();
            httpResponser.protocol = urlConnection.getURL().getProtocol();
            httpResponser.query = urlConnection.getURL().getQuery();
            httpResponser.ref = urlConnection.getURL().getRef();
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();

            httpResponser.content = new String(temp.toString().getBytes(), ecod);
            httpResponser.contentEncoding = ecod;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();

            return httpResponser;
        } catch (IOException e) {
            throw e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }


    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }


    public void setDefaultContentEncoding(String defaultContentEncoding) {
        this.defaultContentEncoding = defaultContentEncoding;
    }

    public static void main(String[] aa) throws IOException {
        HttpRequestWithXml req = new HttpRequestWithXml();
        HttpRespons resp = req.sendPost("http://localhost:8080/gamecms/test/index",
                "aaaaaa");

        System.out.println(resp.getContent());
    }

}
