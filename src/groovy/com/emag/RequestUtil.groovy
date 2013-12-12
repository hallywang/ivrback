package com.emag

import com.aspire.gentox.phonesect.model.GetPhone
import groovy.sql.Sql
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.servlet.http.HttpServletRequest
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpException
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.httpclient.NameValuePair
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.RequestEntity
import org.apache.commons.httpclient.methods.StringRequestEntity
import org.apache.commons.io.IOUtils
import org.apache.log4j.Logger

/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2008-12-23
 * 时间: 10:14:26
 * 说明: 该类实现。。。。
 * 修改人:王海利
 * 修改时间: 2008-12-23 10:14:26
 */

public class RequestUtil {

  private static Logger log = Logger.getLogger(RequestUtil.class);

  def static getIP(HttpServletRequest request) {
    /* String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }*/
    String ip = request.getRemoteAddr();
    return ip;
  }

  def static withXmlHttpRequest(def url, def xml, def callback) {
    def timeout = 60000 * 2
    def client = new HttpClient()
    client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
    client.getHttpConnectionManager().getParams().setSoTimeout(timeout)
    PostMethod post = new PostMethod(url)
    RequestEntity entity = new StringRequestEntity(xml, "text/xml", "utf-8");
    post.setRequestEntity(entity)
    def rspBody = null
    def exception = null
    def statusCode
    try {
      statusCode = client.executeMethod(post)
      if (statusCode == HttpStatus.SC_OK) {
        String charset = post.getResponseCharSet();
        if (!charset) charset = "UTF-8";
        rspBody = IOUtils.toString(post.getResponseBodyAsStream(), charset);
      }
    } catch (HttpException e) {
      exception = e;
      e.printStackTrace()
    } catch (IOException ioe) {
      exception = ioe;
      ioe.printStackTrace()
    } finally {
      post.releaseConnection()
    }
    callback(rspBody, statusCode, exception)
  }

  def static withHttpRequest(def url, def args, def callback) {
    println args
    println url
    def timeout = 3000
    def i = 0
    NameValuePair[] data = new NameValuePair[args?.size()]
    args?.each {k, v ->
      data[i] = new NameValuePair(k, v)
      i++
    }
    def client = new HttpClient()
    client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
    client.getHttpConnectionManager().getParams().setSoTimeout(timeout)
    def post = new PostMethod(url)
    post.setRequestBody(data)
    def rspBody = null
    def exception = null
    def statusCode
    try {
      statusCode = client.executeMethod(post)
      if (statusCode == HttpStatus.SC_OK) {
        rspBody = post.responseBodyAsString
      }
    } catch (HttpException e) {
      exception = e;
      e.printStackTrace()
    } catch (IOException ioe) {
      exception = ioe;
      ioe.printStackTrace()
    } finally {
      post.releaseConnection()
    }
    println statusCode
    callback(rspBody, statusCode, exception)
  }

  def static getTelFromDB(def viva_sid) {

    def sql = Sql.newInstance("jdbc:oracle:thin:@192.168.20.251:1521:viva", "vivame",
            "j7Z,hKrahX9C,NdV", "oracle.jdbc.driver.OracleDriver")
    def row = sql.firstRow("select tel from viva_user where id = (select max(user_id) from viva_online where session_id = ${viva_sid})")

    if (row) {
      log.info "loading from db:${viva_sid},${row.tel}"
      return row.tel
    } else {
      log.error "无法根据viva_sid获取到用户手机号,viva_sid=${viva_sid}"
    }
    return null

  }

  def static readConfigFromFile(def filePath, def encode) {

    def p = [:]

    InputStream inp = new FileInputStream(filePath)


    inp.splitEachLine("=", encode) {
      if (it && it.size() == 2) {
        p.put(it[0], it[1])
      }
    }

    inp.close()

    return p


  }

  /**
   *
   * @param url
   * @param parameters
   * @return  http://www.g.cn?a=xx&b=yy
   */
  public static String mapToUrlParams(String url, Map parameters) {
    StringBuffer param = new StringBuffer();
    int i = 0;
    for (Map.Entry<String, String> entry: parameters.entrySet()) {
      if (i == 0) {
        param.append("?");
      } else {
        param.append("&");
      }
      param.append(entry.getKey()).append("=").append(entry.getValue());
      i++;
    }
    url += param;

    return url;
  }

/*
  public static void main(String[] ar) {

    // normal strings
    def firstname = 'Kate'
    def surname = "Bush"
    assert firstname * 2 == 'KateKate'
// GString
    def fullname = "$firstname $surname"
    assert fullname == 'Kate Bush'
    assert fullname - firstname == ' Bush'
    assert fullname.padLeft(10) ==
            ' Kate Bush'
// indexing (including ranges)
    assert fullname[0..3] == firstname
    assert fullname[-4..-1] == surname
    assert fullname[5, 3..1] == 'Beta'

    println """
----------------------
|    $fullname |
|    123 First Ave    |
|    New York         |
----------------------
"""
    use(TimeCategory) {
      println new Date() + 1.hour + 3.weeks - 2.days
    }
    assert [1, 2, 3, 4] == (1..4)
    assert [1, 2, 3] + [1] == [1, 2, 3, 1]
    assert [1, 2, 3] << 1 == [1, 2, 3, 1]
    assert [1, 2, 3, 1] - [1] == [2, 3]
    assert [1, 2, 3] * 2 == [1, 2, 3, 1, 2, 3]
    assert [1, [2, 3]].flatten() == [1, 2, 3]
    assert [1, 2, 3].reverse() == [3, 2, 1]
    assert [1, 2, 3].disjoint([4, 5, 6])
    assert [1, 2, 3].intersect([4, 3, 1]) == [3, 1]
    assert [1, 2, 3].collect { it + 3 } == [4, 5, 6]
    assert [1, 2, 3, 1].unique().size() == 3
    assert [1, 2, 3, 1].count(1) == 2
    assert [1, 2, 3, 4].min() == 1
    assert [1, 2, 3, 4].max() == 4
    assert [1, 2, 3, 4].sum() == 10
    assert [4, 2, 1, 3].sort() == [1, 2, 3, 4]
    assert [4, 2, 1, 3].findAll { it % 2 == 0 } == [4, 2]


  }
*/

  public static void main(String[] ars) {

    /*long a = System.currentTimeMillis()
    Pattern pattern = Pattern.compile(".*(hi|mm|胡锦涛).*");
    Matcher matcher = pattern.matcher("呵呵")
    boolean b = matcher.matches();


    long c = System.currentTimeMillis()

    System.out.println(b.toString()+"===" + (c - a));*/
    println GetPhone.getInstance().getCity_id("13675180163")
    println GetPhone.getInstance().getProv_id("13675180163")

    println "你好我吗d".getBytes().length

  }
}