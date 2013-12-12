package com.emag.wabao

/**
 * 挖宝配置文件
 * User: guoqiang
 * Date: 12-6-20
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
class WaBaoConstants {

    /**
     * 省份校验器名称
     */
    public static final String WABAO_BASE_PRINAME = "省份校验器"

    /**
     * 活动省份校验器类路径
     */
    public static final String WABAO_BASE_PRIAUTH = "com.emag.wabao.auth.ProvinceAuth"

    /**
     * 黑名单校验器名称
     */
    public static final String WABAO_BASE_BLACKUSERNAME = "黑名单校验器"

    /**
     * 活动黑名单校验器类路径
     */
    public static final String WABAO_BASE_BLACKUSERAUTH = "com.emag.wabao.auth.BlackUserAuth"

    /**
     * 套餐包校验器名称
     */
    public static final String WABAO_BASE_PACKORDNAME = "套餐包订购校验器"

    /**
     * 活动黑名单校验器路径
     */
    public static final String WABAO_BASE_PACKORDAUTH = "com.emag.wabao.auth.PackageOrderAuth"

    /**
     * 奖品库校验器路径
     */
    public static final String WABAO_STORE_DUPVAL = "com.emag.wabao.validator.DuplicateHitValidator"

    /**
     * 乐豆奖品记录日志
     */
    public static final String WABAO_AWARD_LOG4LDNAME = "记录日志"

    /**
     * 乐豆奖品记录日志的路径
     */
    public static final String WABAO_AWARD_LOG4LDOPER = "com.emag.wabao.operation.Log4AwardOperation"

    /**
     * 中奖发送短信通知用户
     */
    public static final String WABAO_AWARD_SMSNAME = "发送短信"

    /**
     * 中奖发送短信通知用户路径
     */
    public static final String WABAO_AWARD_SMAOPER = "com.emag.wabao.operation.Sms4AwardOperation"

    /**
     * 铁锹获取方式：下载单机
     */
    public static final Integer WABAO_GAIN_WAY_DOWN_SPADE = 2

    /**
     * 固定时间段：今日
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_THIS_DAY = 100

    /**
     * 固定时间段：本周
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_THIS_WEEK = 101

    /**
     * 固定时间段：本月
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_THIS_MONTH = 102

    /**
     * 固定时间段：今年
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_THIS_YEAR = 103

    /**
     * 固定天数，固定从用户获取铁锹时间算起多少天内有效
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_CERTAIN_DAYS = 200

    /**
     * 固定时间点、表示到某一个确定的时间点（如2012年12月31日）有效
     */
    public static final String WABAO_GAIN_WAY_VALID_TYPE_ABSOLUTE_DATE = 300

}
