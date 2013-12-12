package com.emag.util

import com.vivame.util.StringUtil
import java.util.Map.Entry

/**
 * Created by IntelliJ IDEA.
 * User: hp
 * Date: 2010-8-26
 * Time: 17:26:58
 * To change this template use File | Settings | File Templates.
 */
class UriTool {

  private String tmpUri;

  private String originalUri = "";

  private String finalUri = ""

  private Map<String, String> finalMap = new LinkedHashMap<String, String>();

  private Map<String, String> tmpMap;

  public UriTool(String tmpUri, Map<String, String> tmpMap) {
     this.tmpUri = tmpUri;
     this.tmpMap = tmpMap;
  }

  public void parseUri() {
    if (StringUtil.isEmpty(tmpUri)) {
      return;
    }

    originalUri = tmpUri;

    //获取最原始的uri地址，该地址不带任何参数
    String paramStr = "";
    if (tmpUri.indexOf("?") > 0) {
      //uri地址存在参数
      originalUri = tmpUri.substring(0, tmpUri.indexOf("?"))
      paramStr = tmpUri.substring(tmpUri.indexOf("?") + 1)
    }

    //获取uri地址后面直接跟的参数，并将参数信息记录在 finalMap 中
    if (!StringUtil.isEmpty(paramStr)) {
      paramStr = paramStr.replaceAll("&amp;", "&");
      String[] paramArray = paramStr.trim().split("&")
      paramArray?.each {
        if (it.indexOf("=") > 0) {
          finalMap.put(it.split("=")[0], it.split("=")[1])
        }
      }
    }

    if (tmpMap != null && tmpMap.size() > 0) {
      finalMap.putAll(tmpMap)
    }

    //拼装完整的uri，将所有参数加入到uri地址后面
    finalUri = originalUri
    int paramNo = 0;
    for (Entry<String, String> entry : finalMap.entrySet()) {
       paramNo++

       //第一个参数前用 ？ 与uri地址连接
       if (paramNo == 1) {
         finalUri = finalUri + "?"
       } else {
         finalUri = finalUri + "&"
       }

       finalUri = finalUri + entry.getKey() + "=" + entry.getValue()
    }
  }

  public static void main(String[] args) {
    String uri = "http://localhost:8080/gamecms/wap/hytj/input?a=1&amp;b=2&c=3"
    Map<String, String> map = new HashMap<String, String>();
    map.put("d", "4");
    map.put("e", "5")
    

    UriTool tool = new UriTool(uri, map);
    tool.parseUri();

    println tool.tmpUri
    println tool.originalUri
    println tool.finalMap
    println tool.finalUri

  }
}
