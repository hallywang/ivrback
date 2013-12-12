package com.emag.gamecms.service

import com.emag.ObjectCache
import com.emag.constants.MemcacheConstants
import org.apache.commons.lang.StringUtils
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler

class InitService {

    def memcachedService
    def middleDataSource

    /**
     * 加载手机号段信息到缓存
     * @return
     *
     * @author:RongWei
     * @date:2011-02-15
     */
    def initMobileSegment() {
        long startTime = System.currentTimeMillis()
        log.info " start to load mobile segment list from db ... "

        ObjectCache objCache = memcachedService.getCache(MemcacheConstants.CACHE_MOBILE_SEGMENT)
        StringBuilder buff = new StringBuilder()
        buff.append("select mobile_prefix,")
        buff.append("       city_id,")
        buff.append("       city_name,")
        buff.append("       province_id,")
        buff.append("       province_name,")
        buff.append("       county_id,")
        buff.append("       county_name")
        buff.append("  from tb_numbelinfo")
        def rowCallback = {java.sql.ResultSet rs ->
            String prefix = StringUtils.trimToEmpty(rs.getString('mobile_prefix'))

            //号段信息
            StringBuilder segmentInfo = new StringBuilder()
            segmentInfo.append(prefix).append(';') //号段
            segmentInfo << StringUtils.trimToEmpty(rs.getString('city_id')) << '-' << StringUtils.trimToEmpty(rs.getString('city_name')) << ';' //城市信息
            segmentInfo << StringUtils.trimToEmpty(rs.getString('province_id')) << '-' << StringUtils.trimToEmpty(rs.getString('province_name')) << ';'//  省份信息
            segmentInfo << StringUtils.trimToEmpty(rs.getString('county_id')) << '-' << StringUtils.trimToEmpty(rs.getString('county_name')) << ';' //国家信息

            objCache.put(prefix, segmentInfo.toString())
        } as RowCallbackHandler

        JdbcTemplate jdbcTemplate = new JdbcTemplate(middleDataSource)
        jdbcTemplate.query(buff.toString(), rowCallback)

        long endTime = System.currentTimeMillis()
        log.info " end to load mobile segment list from db cost:${(endTime - startTime) / 1000}s ... "
    }

}
