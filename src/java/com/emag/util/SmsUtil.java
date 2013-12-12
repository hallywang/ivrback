package com.emag.util;

import com.emag.url.HttpUtil;
import com.eoms2.sms.adapter.RmiAdapter;
import com.vivame.util.StringUtil;
import com.vivame.util.TimeUtil;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: hp Date: 2010-3-31 Time: 10:24:55 To change this template use File | Settings | File
 * Templates.
 */
public class SmsUtil {

    private static Logger log = Logger.getLogger(SmsUtil.class);

    /**
     * 发送短信
     *
     * @param smsContent 短信内容
     * @param mobile     手机号码
     */
    public static void sendSms(String smsContent, String mobile) {
        StringBuilder buff = new StringBuilder();
        buff.append("<root>");
        buff.append("<username>").append(ParamComm.getParam("rmisms", "username")).append("</username>");
        buff.append("<password>").append(ParamComm.getParam("rmisms", "password")).append("</password>");
        buff.append("<message>");
        buff.append("<address>").append(mobile).append("</address>");
        buff.append("<content>").append(smsContent).append("</content>");
        buff.append("</message>");
        buff.append("</root>");
        try {
            RmiAdapter rmi = (RmiAdapter) Naming.lookup(ParamComm.getParam("rmisms", "rmi_address"));
            rmi.sendSms(buff.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param url      wappush url
     * @param title    标题
     * @param mobile   手机号
     * @param sendTime 发送时间 null为立即发送
     * @return 0  成功 ，1 失败
     */
    public static int sendWapPush(String url, String title, String mobile, String sendTime) {
        int ret = 0;


        if (url == null || "".equals(url) || title == null || "".equals(title) || mobile == null || "".equals(mobile)) {
            return 1;
        }
        if (sendTime == null || "".equals(sendTime)) {
            sendTime = TimeUtil.getTime();
        }
        //?type=1&tel=13675180163&url=http://g.cn&title=g&
        // sendtime=20100505132100&service_code=&task_id=&destnation_id=1065883899
        String sendurl = ParamComm.getParam("rmisms", "wappush.url");
        String destnation_id = ParamComm.getParam("rmisms", "wappush.destnationId");
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("type", "1");
        params.put("tel", mobile);

        params.put("url", url);
        params.put("title", title);

        params.put("sendtime", sendTime);
        // params.put("service_code", "");
        // params.put("task_id", "");
        params.put("destnation_id", destnation_id);

        try {
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.setConnectionTimeout(1000);
            httpUtil.setReadTimeout(1000);
            String content = httpUtil.sendGet(sendurl, params, null);
            log.info("wappushsend url=" + url + ",mobile=" + mobile + ",title=" + title + "," + content);
        } catch (Exception e) {
            log.error(e);
            ret = 1;
        }
        return ret;

    }

     /**
     * 发送短信
     *
     * @param smsContent 短信内容
     * @param mobile     手机号码，11位
     * @param sendTime   发送时间
     * @return -1 调用接口失败
     *         -2 解析xml失败
     *         0，则表示处理成功;
     *         1001，则表示某个数据值不存在（如：参数tel不存在，返回：request请求中缺少参数tel的值）；
     *         2001，则表示数据类型不对；
     *         3001，则表示数据格式不正确
     *         4001，则表示数据库操作出错；
     *         5001，则表示获取数据库连接出错;
     *         5002，则表示获取到的数据库连接为空;
     *         6001，则表示调用者IP非法
     *         6002，则表示短信内容或push标题含有敏感字
     *         6003，则表示用户名或密码错误
     *         6004，则表示调用过于频繁
     */
    public static int sendSms8899(String smsContent, String mobile, String sendTime) {
        //默认返回错误代码，调用接口发送短信失败
        int rtnCode = -1;

        String url = ParamComm.getParam("rmisms", "wappush8899.url");
        String username = ParamComm.getParam("rmisms", "wappush8899.username");
        String password = ParamComm.getParam("rmisms", "wappush8899.password");
        String sTime = StringUtil.isEmpty(sendTime) ? TimeUtil.getTime() : sendTime;

        // 调用接口，返回值
        String response = "";
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("tel", mobile);
            params.put("type", "0");
            params.put("content", URLEncoder.encode(smsContent, "GBK"));
            params.put("sendtime", sTime);
            params.put("user", URLEncoder.encode(username, "GBK"));
            params.put("pwd", URLEncoder.encode(password, "GBK"));

            HttpUtil http = new HttpUtil();
            http.setConnectionTimeout(3000);
            http.setReadTimeout(3000);
            response = http.sendGetNotEncode(url, params, null);
        } catch (Exception ex) {
            log.error("使用wappush8899发送短信时出现错误，调用接口失败：", ex);
            return rtnCode;
        }

        //解析返回值
        String[] respArray = parsePush8899xml(response);
        rtnCode = Integer.parseInt(respArray[0]);
        log.info("wappush8899 发送短信,接口参数[tel:" + mobile + ",type:0,content:" + smsContent +
                ",httpurl:" + url + ",user:" + username + ",pwd:" + password + ",sendtime:" + sTime + "]，返回状态码：" + rtnCode + "，描述：" + respArray[1]);

        return rtnCode;
    }

    /**
     * @param title    消息标题
     * @param url      消息url
     * @param mobile   手机号码
     * @param sendTime 发送时间，为空字符串或null就表示立即发送
     * @return 状态码
     *         -1 调用接口失败
     *         -2 解析xml失败
     *         0，则表示处理成功;
     *         1001，则表示某个数据值不存在（如：参数tel不存在，返回：request请求中缺少参数tel的值）；
     *         2001，则表示数据类型不对；
     *         3001，则表示数据格式不正确
     *         4001，则表示数据库操作出错；
     *         5001，则表示获取数据库连接出错;
     *         5002，则表示获取到的数据库连接为空;
     *         6001，则表示调用者IP非法
     *         6002，则表示短信内容或push标题含有敏感字
     *         6003，则表示用户名或密码错误
     *         6004，则表示调用过于频繁
     */
    public static int sendWappush8899(String title, String url, String mobile, String sendTime) {
        //默认返回错误代码，调用接口发送短信失败
        int rtnCode = -1;

        String httpUrl = ParamComm.getParam("rmisms", "wappush8899.url");
        String username = ParamComm.getParam("rmisms", "wappush8899.username");
        String password = ParamComm.getParam("rmisms", "wappush8899.password");
        String sTime = StringUtil.isEmpty(sendTime) ? TimeUtil.getTime() : sendTime;

        // 调用接口，返回值
        String response = "";
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("tel", mobile);
            params.put("type", "1");
            params.put("title", URLEncoder.encode(title, "GBK"));
            params.put("url", URLEncoder.encode(url, "GBK"));
            params.put("sendtime", sTime);
            params.put("user", URLEncoder.encode(username, "GBK"));
            params.put("pwd", URLEncoder.encode(password, "GBK"));


            HttpUtil http = new HttpUtil();
            http.setConnectionTimeout(3000);
            http.setReadTimeout(3000);
            response = http.sendGetNotEncode(httpUrl, params, null);
        } catch (Exception ex) {
            log.error("使用wappush8899发送wappush时出现错误，调用接口失败：", ex);
            return rtnCode;
        }

        //解析返回值
        String[] respArray = parsePush8899xml(response);
        rtnCode = Integer.parseInt(respArray[0]);
        log.info("wappush8899 发送wappush,接口参数[tel:" + mobile + ",type:1,title:" + title +
                ",url:" + url + ",user:" + username + ",pwd:" + password + ",sendtime:" + sTime + "]，返回状态码：" + rtnCode + "，描述：" + respArray[1]);
        return rtnCode;
    }

    /**
     * 解析8899返回的xml代码，解析失败返回["-2", ""]，否则返回xml中的返回值和描述值
     *
     * @param xmlContent XML内容
     * @return String[]
     */
    private static String[] parsePush8899xml(String xmlContent) {
        String[] rtnArray = new String[]{"-2", "解析失败"};

        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(new ByteArrayInputStream(xmlContent.getBytes("utf-8")));

            Element root = doc.getRootElement();
            rtnArray[0] = ((Text) XPath.selectSingleNode(root, "//resp/result-code/text()")).getTextNormalize();
            rtnArray[1] = ((Text) XPath.selectSingleNode(root, "//resp/remind-words/text()")).getTextNormalize();
            rtnArray[1] = new String(rtnArray[1].getBytes("iso-8859-1"), "utf-8");

        } catch (Exception ex) {
            rtnArray = new String[]{"-2", "解析失败"};
        }

        return rtnArray;
    }

    public static void main(String[] aa) {
        //sendWapPush("http://www.g.cn", "王海利测试试试", "13675180163", "20100916111111");
        sendSms("李晖测试","15805154358");
    }
}
