package com.emag.util

import java.util.regex.Matcher
import java.util.regex.Pattern
import org.apache.log4j.Logger
import com.aspire.gentox.phonesect.model.GetPhone

/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2010-1-26
 * 时间: 15:02:34
 * 说明: 该类实现。。。。
 * 修改人:王海利
 * 修改时间: 2010-1-26 15:02:34
 */
class MobileUtil {

  private static Logger log = Logger.getLogger(MobileUtil.class)
  /**
   * 由于某些手机传过来的中文会是 &#23553; 编码，增加该方法
   * @param unicodeString &#编码的中文
   * @return 正常中文
   */
  public static String unicodeToCh(String unicodeString) {
    StringBuffer s = new StringBuffer();
    Pattern key = Pattern.compile("&#\\d+\\;");
    try {
      Matcher matcher = key.matcher(unicodeString);

      while (matcher.find()) {
        String a = matcher.group();
        s.append((char) Integer.parseInt(a.substring(2, a.length() - 1)));
      }
    } catch (Exception e) {
      log.error e
      s = null
    }
    if (s) {
      return s.toString();
    } else {
      return unicodeString;
    }


  }

  def static matchTel(def s) {   //todo
    String regEx = "(?<=\\D)\\(?0\\d{2,3}[) -]?\\d{7,8}|1\\d{10}|(?<=\\D)[2-9]\\d{7}(?=\\D)";

    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(s);
  }

  def static getMobile(def request) {
    def mobile = request.getHeader("x-up-calling-line-id")

    if (!mobile) mobile = request.getHeader("phone") //MM传这个过来

    if (mobile && mobile.length() >= 11) {
      mobile = mobile[-11..-1]  //只取后11位
    } else if (mobile == null) {
      mobile = ""
    }
    return mobile

  }
  def static getCityId(def request){
    int city_id = GetPhone.getInstance().getCity_id(getMobile(request));
    return city_id;
  }
  
  def static getProvId(def request){
    int prov_id = GetPhone.getInstance().getProv_id(getMobile(request))
    return prov_id
  }


  public static void main(String[] args) {


  /* def i = 200/100/0.8
   println i.setScale(0, BigDecimal.ROUND_HALF_UP)*/


   println GetPhone.getInstance().getProv_id("14790830000")

   println GetPhone.getInstance().getCityName("14790830000")

       println GetPhone.getInstance().getCityName("13675180163")

  println  URLDecoder.decode("http%3A%2F%2Fgmp.i139.cn%2Fbizcontrol%2FWapProxy%3Fsender%3D202%26cpServiceId%3D210222033000%26channelId%3D15042000","UTF-8")

  }

}
