package com.emag.util

import java.util.regex.Pattern
import java.util.regex.Matcher

/**
 * 功能描述
 * User: huzl
 * Date: 2010-5-31
 * Time: 13:29:37
 * 幻方朗睿软件科技有限公司南京分公司&copy;2010
 */
class RegularExpressionUtils {
  public static final Pattern EntityTagPattern = Pattern.compile("<(\\w+?)[^<>]*?>[^<>]*?</\\1>",Pattern.MULTILINE);
  public static final Pattern ImgTagPattern = ~/(?i)<img\s+src=(['"])(.+?)\1\s*\/?>/
  public static final Pattern EmptyTagPattern = Pattern.compile("<[^<>]*?/>",Pattern.MULTILINE);

  public static final Pattern newsContent = ~/(?i)#([a-z\d]+\.)(jpg|gif|png|bmp)?/    //add by wanghl used by newsinfo
  public static final Pattern keyOrderPagePattern = ~/(?i)<a\s+href=(['"])(.+?)\1\s*\/?>/ //add by wanghl used by orderpage key

  /**
   * 替换a标签中的href
   * @param content wml代码内容
   * @param key  把原地址转换为新地址接口
   * @return 新wml，订购地址加入key
   */
  public static String addKeyToOrderA(String content,String key){
    Matcher m = keyOrderPagePattern.matcher(content);
    StringBuffer buf = new StringBuffer();
    while(m.find()){
      String url = m.group(2)+"&amp;key="+key;
      String imgTagContent = "<a href=\"$url\">"
      m.appendReplacement(buf,imgTagContent)
    }
    m.appendTail(buf);
    return buf.toString();
  }

  /**
   * 替换资源图片地址
   * @param content 图文混排内容
   * @param translateUrl  把原地址转换为正确的<img src="" alt=""/>内容
   * @return 新的图文混排内容
   */
  public static String replaceNewsContent(String content,Closure translateUrl){
    Matcher m = newsContent.matcher(content);
    StringBuffer buf = new StringBuffer();
    while(m.find()){
      String url = translateUrl.call(m.group(1)+m.group(2));
      String imgTagContent = "<img src=\"$url\" alt=\".\"/>"
      m.appendReplacement(buf,imgTagContent)
    }
    m.appendTail(buf);
    return buf.toString();
  }
  /**
   * 删除内容中含有的标签
   * @param content
   * @return
   */
  public static String removeTag(String content){
    if(!content)return "";
    if(content.indexOf("<")<0)return content;
    content = content.replaceAll(EntityTagPattern,"");
    return content.replaceAll(EmptyTagPattern,"");
  }

  /**
   * 替换资源图片地址
   * @param content 图文混排内容
   * @param translateUrl  把原地址转换为新地址接口
   * @return 新的图文混排内容
   */
  public static String replaceImgUrl(String content,Closure translateUrl){
    Matcher m = ImgTagPattern.matcher(content);
    StringBuffer buf = new StringBuffer();
    while(m.find()){
      String url = translateUrl.call(m.group(2));
      String imgTagContent = "<img src=\"$url\"/>"
      m.appendReplacement(buf,imgTagContent)
    }
    m.appendTail(buf);
    return buf.toString();
  }



  public static void main(String[] args) {
    String str = "1\n<a href='3'>sadfadf</a>2222\n33333<img src=\'2342\' />iugiugoiugo<img src=\'2342\' />\n444444444444<img>asdfadf</img>";
   /* println replaceImgUrl(str,{url->
      return "http://wap.g188.net"+ url;
    });*/
    str="df天啊 #adfadsf.jpg你好啊哦哦哦#adfadsFG.JPG22222#1.giffff#f3.gifhhh#h4.pngttt#a.bmpff"
    println str
     println replaceNewsContent(str,{url->
      return "/cms/gamecms/newsinfo/"+ url;
    });
  }
}
