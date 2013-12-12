package com.vivame.groovy.util

import com.emag.gamecms.domain.city.Province
import com.vivame.util.StringUtil
import org.springframework.web.context.support.WebApplicationContextUtils
import org.codehaus.groovy.grails.commons.ApplicationHolder

/**
 * Created by IntelliJ IDEA.
 * User: hp
 * Date: 2010-11-23
 * Time: 9:17:32
 * To change this template use File | Settings | File Templates.
 */
class ProvinceAndCityUtil {
  /**
   *  保存省份信息
   *  key：省份的prov_id（江苏250，广东200...）
   *  value：省份对象
   */
  private static final Map<String, Province> PROVINCE_MAP_PROV_ID = new HashMap<String, Province>();

  /**
   *  保存省份信息
   *  key：省份的主键id（江苏63，广东79...）
   *  value：省份对象
   */
  private static final Map<Long, Province> PROVINCE_MAP_ID = new HashMap<String, Province>();

  /**
   * 根据省份实际的id（江苏为250，广东为200...）来获取省份对象
   * 获取不到，则直接返回null
   */
  public static synchronized Province getProvinceByProvId(String provId) {
    def provinceAndCityService = grails.util.Holders.applicationContext.getBean('provinceAndCityService');
    if (PROVINCE_MAP_PROV_ID.containsKey(provId)) {
      return PROVINCE_MAP_PROV_ID.get(provId);
    }

    def province = provinceAndCityService.getProvinceByProvId(provId);
    if (province) {
      PROVINCE_MAP_PROV_ID.put(provId, province);
      return province;
    }

    return null;
  }

  /**
   * 根据省份表的主键id来获取省份对象
   * 获取不到，则直接返回null
   */
  public static synchronized Province getProvinceById(Long id) {
    def provinceAndCityService = grails.util.Holders.applicationContext.getBean('provinceAndCityService');
    if (PROVINCE_MAP_ID.containsKey(id)) {
      return PROVINCE_MAP_ID.get(id);
    }

    def province = provinceAndCityService.getProvinceById(id);
    if (province) {
      PROVINCE_MAP_ID.put(id, province);
      return province;
    }

    return null;
  }

  /**
   * 根据多个省份id，来获取各省份的中文名，
   * 返回的省份中文名之间仍然使用省份id之间的分隔符
   * 如：省份id为 200|250，则本方法返回结果是：广东|江苏
   */
  public static String getProvinceNamesByProvIds(String provIds, String splitStr) {
    if (StringUtil.isEmpty(provIds)) {
      return '';
    }

    if (StringUtil.isEmpty(splitStr)) {
      return provIds;
    }

    StringBuffer buff = new StringBuffer();
    provIds.split(splitStr)?.each {provId ->
      def tmpProvince = getProvinceByProvId(provId);
      if (tmpProvince) {
        buff.append(splitStr);
        buff.append(tmpProvince.provName);
      }
    }

    return buff.toString() ? buff.toString().substring(1) : '';
  }
}
