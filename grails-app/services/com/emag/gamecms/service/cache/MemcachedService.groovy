package com.emag.gamecms.service.cache

import com.emag.ObjectCache
import com.emag.ObjectMemcache

class MemcachedService {
  boolean transactional = false

  def memcachedClient

  ObjectCache getCache(cacheName) {
    return new ObjectMemcache(memcachedClient, cacheName)
  }
}