package com.emag

import net.sf.ehcache.Cache
import net.sf.ehcache.Element

/**
 * Created by IntelliJ IDEA.
 * User: huzl
 * Date: 2010-3-3
 * Time: 14:04:30
 * To change this template use File | Settings | File Templates.
 */
class ObjectEchache implements ObjectCache{
  Cache cache
  def ObjectEchache(cache) {
    this.cache = cache;
  }

  def cleanCache() {
    cache.removeAll()
  }

  def put(Object key, Object value) {
    Element element = new Element(key, value);
    cache.put(element);
  }

  Object get(Object key) {
    Element element = cache.get(key);
    if (element) {
      Object value = element.getObjectValue();
      return value
    } else {
      return null
    }
  }

  def int size() {
    return cache.getSize();
  }

  def remove(Object key) {
    return cache.remove(key)
  }

  /**
   * 实现获取所有key
   * @return
   *
   * @editor:RongWei
   * @editDate:2011-04-19
   */
  public List getKeys(){
    return cache.getKeys();
  }

  def put(Object key, Object value, Object time) {
    put(key, value)
  }
}
