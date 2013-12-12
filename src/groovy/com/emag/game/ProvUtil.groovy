package com.emag.game

import com.emag.gamecms.domain.city.Province
import com.emag.gamecms.domain.city.City

/**
 * emagsoftware All Rights Reserved@2010-2011
 * 作者: 王海利
 * 日期: 2010-2-2
 * 时间: 9:39:02
 * 说明: 该类实现。。。。
 * 修改人:王海利
 * 修改时间: 2010-2-2 9:39:02
 */
class ProvUtil {
 public static def showProvs(def provs) {
    def result = new StringBuffer()
    def ps = provs?.split(",")

    def provList = Province.list()
    def provMap = [:]
    provList.each {
      provMap.put(String.valueOf(it.provId), it.provName)
    }
    ps.each {
      result.append(provMap.get(it))
      result.append(",")
    }

    return result.toString()
  }


 /**
  * 根据cityid来获取地址的中文名 <br/>
  * @param cityIds 城市id，多个城市以逗号分隔，每个城市id的格式如：省id-市cityid
  * @param provinceId 省id，不为空时表示需要通过省id来过滤市，对于不是该省的地市不显示
  */
  public static String showCityCnName(String cityIds, String provinceId) {
    if (!cityIds) {
      return '';
    }

    StringBuffer rtnCode = new StringBuffer();
    cityIds.split(',').each {
        String provId = it.split('-')[0]
        String cityId =  it.split('-')[1]
        if (provinceId && provId != provinceId) {
          //需要用省id来过滤，但是该市不属于该省，所以不做显示
        } else {
          rtnCode.append(',').append(City.findByCityId(cityId)?.cityName)
        }
    }

    //存在逗号表面有地市信息
    return rtnCode.toString().indexOf(',') >= 0 ? rtnCode.toString().substring(1) : '';
  }
}
