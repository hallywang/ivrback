package com.emag.constants

/**
 * Ehcache的常量类
 * 本类主要用来定义ehcache的名称，申明变量时统一以CACHE开头，缓存名统一格式为“缓存KEY-缓存VALUE”
 * User: linguangfa
 * Date: 2011-7-1
 * Time: 15:08:07
 * To change this template use File | Settings | File Templates.
 */
class EhcacheConstants {

  /** 机型信息缓存，key为机型id，value为机型对象   */
  public static final String CACHE_MIDDLE_UA = 'uaid-ua'

  /** 系列信息缓存，key为系列id，value为系列对象   */
  public static final String CACHE_MIDDLE_GROUP = 'groupid-group'

  /** 厂商信息缓存，key为厂商英文名，value为该厂商的系列id列表   */
  public static final String CACHE_MIDDLE_FACTORY = 'factoryname-groupidset'

  /** 号段缓存，key为7\8位号段，value为该号段信息（包括城市信息，省份信息等）   */
  public static final String CACHE_MOBILE_SEGMENT = 'mobileprefix-segmentinfo'

  /** 分省暂停包缓存 ，key为省份id,value为暂停的游戏包  */
  public static final String CACHE_PACKAGE_PAUSE = 'provinceid-packageidset'

  /** 分省暂停业务，key为省份id,value为暂停的业务  */
  public static final String CACHE_GAME_PAUSE = 'provinceid-cpserviceidset'

  /** sp信息，key为sp_code，value为sp对象  */
  public static final String CACHE_SP_INFO = 'spcode-sp'

  /** 按游戏下载总量的一个cp列表，key为cpCache（固定的string值），value为一个排好序的cplist */
  public static final String CACHE_CPLIST = 'cpcachestr-ordercplist'

  /** 游戏缓存 */
  public static final String CACHE_VIVAMAG_GAMES = 'vivamagCache'

  /** ip地址缓存 */
  public static final String CACHE_IP_ADDRESS = 'flag-ipstrlist'

}
