package com.emag.util

import java.util.regex.Pattern

/**
 * 号段检查
 * 本类主要提供校验手机号码校验，移动号码校验，联通号码校验，电信号码校验
 * User: lingf
 * Date: 2010-9-3
 * Time: 13:31:57
 * To change this template use File | Settings | File Templates.
 */
class MobileSegmentUtil {

  //手机号码的正则表达式
  private static final String MOBILE_REGEX = "^(13|15|18)[0-9]{9}\$"

  //中国移动手机号码的正则表达式
  private static final String CHINA_MOBILE_REGEX = "^(13[4-9]|15[012789]|18[7-8])[0-9]{8}\$"

  //中国联通手机号码的正则表达式
  private static final String CHINA_UNICOM_REGEX = "^(13[012]|15[56]|18[56])[0-9]{8}\$"

  //中国电信手机号码的正则表达式
  private static final String CHINA_TELECOM_REGEX = "^(133|153|18[09])[0-9]{8}\$"

  /**
   * 校验输入是否是手机号码
   */
  public static boolean isMobile(String input) {
    if (!input) {
      return false;
    }

    return Pattern.compile(MOBILE_REGEX).matcher(input).matches();
  }

  /**
   * 校验输入是否是中国移动号码
   */
  public static boolean isChinaMobile(String input) {
    if (!input) {
      return false;
    }

    return Pattern.compile(CHINA_MOBILE_REGEX).matcher(input).matches();
  }

  /**
   * 校验输入是否是中国联通号码
   */
  public static boolean isChinaUnicom(String input) {
    if (!input) {
      return false;
    }

    return Pattern.compile(CHINA_UNICOM_REGEX).matcher(input).matches();
  }

  /**
   * 校验是否是中国电信号码
   */
  public static boolean isChinaTelecom(String input) {
    if (!input) {
      return false;
    }

    return Pattern.compile(CHINA_TELECOM_REGEX).matcher(input).matches();
  }

  public static void main(String[] args) {
    String input = "13445154535"

    println isMobile(input)
    println isChinaMobile(input)
    println isChinaUnicom(input)
    println isChinaTelecom(input)
  }
}
