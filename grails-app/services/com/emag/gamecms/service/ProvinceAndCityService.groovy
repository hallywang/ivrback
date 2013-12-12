package com.emag.gamecms.service

import com.emag.gamecms.domain.city.Province
import com.vivame.util.StringUtil

class ProvinceAndCityService {

    boolean transactional = false

    /**
     * 根据省份实际的id（江苏为250，广东为200...）来获取省份对象
     * 获取不到，则直接返回null
     */
    public Province getProvinceByProvId(String provId) {
        return Province.findByProvId(provId);
    }

    /**
     * 根据省份表的主键id来获取省份对象
     * 获取不到，则直接返回null
     */
    public Province getProvinceById(Long id) {
        return Province.findById(id);
    }

}
