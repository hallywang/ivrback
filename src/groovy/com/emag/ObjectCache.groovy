package com.emag

/**
 * Created by IntelliJ IDEA.
 * User: huzl
 * Date: 2010-3-3
 * Time: 14:01:58
 */
public interface ObjectCache {
  def cleanCache()

  def put(key, value)

  def put(key, value, time)

  Object get(key)

  def remove(key)

  int size()

  List getKeys() //获取缓存中所有的key,@editor:RongWei,@editeDate:2011-04-19
}