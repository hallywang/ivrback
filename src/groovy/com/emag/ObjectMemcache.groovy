package com.emag

import net.rubyeye.xmemcached.MemcachedClient
import com.emag.util.SecurityUtil

/**
 * emag softeware All Rights Reserved@2010-2012
 * 作者: 王海利
 * 日期: 11-6-13
 * 时间: 上午11:31
 * 说明: 该类实现。。。。
 * 修改记录:
 * 1.创建文件
 */
class ObjectMemcache implements ObjectCache {
  /** 缓存名  */
  private String cacheName

  /** xmemcache客户端  */
  private MemcachedClient memcachedClient

  /**
   * 构造函数
   * @param memcachedClient xmemcache客户端
   * @param cacheName 缓存名
   */
  public ObjectMemcache(memcachedClient, cacheName) {
    this.memcachedClient = memcachedClient
    this.cacheName = cacheName
  }

  /**
   * 清除memcache里面的所有内容，太危险，不做任何操作
   */
  def cleanCache() {
    // 不做任何操作
    //memcachedClient.flushAll()
  }

  /**
   * 往memcache中存入值
   * @param key key值
   * @param value 对应的value
   */
  def put(Object key, Object value) {
    put(key, value, 0)
  }

  /**
   * 从memcache中获取数据
   */
  Object get(Object key) {
    memcachedClient.get(SecurityUtil.getMD5String(cacheName + key))
  }

  /**
   * 获取memcache中存放的对象数量
   * xmemcahce客户端未提供api，本返回默认返回0
   */
  int size() {
    return 0
  }

  /**
   * 删除memcache缓存中的数据
   */
  def remove(Object key) {
    memcachedClient.delete(SecurityUtil.getMD5String(cacheName + key))
  }

  /**
   * 获取memcache中所有key值
   * xmemcache客户端未提供api，默认返回一个空列表
   */
  List getKeys() {
    return []
  }

  /**
   * 往memcache中存入数据
   * @param key key值
   * @param value value值
   * @param cacheTimeSeconds 有效期，单位为s
   */
  def put(Object key, Object value, Object cacheTimeSeconds) {
    memcachedClient.set(SecurityUtil.getMD5String(cacheName + key), cacheTimeSeconds, value)
  }
}