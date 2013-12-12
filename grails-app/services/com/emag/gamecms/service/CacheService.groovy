package com.emag.gamecms.service

import com.emag.ObjectCache
import com.emag.ObjectEchache
import javax.sql.DataSource
import net.sf.ehcache.Cache
import net.sf.ehcache.CacheManager

class CacheService {
  public static final String GameinfoCache = "gameinfoCache";
  boolean transactional = false
  public static CacheManager manager = CacheManager.getInstance()
  Cache cache = manager.getCache("vivamagCache")
  DataSource dataSource;
  Map cacheMap = [:];

  def serviceMethod() {
  }

  def cleanCache(cacheName) {
    cache = manager.getCache(cacheName)
    cache.removeAll()
  }


  ObjectCache getCache(cacheName) {
    ObjectCache objectCache = cacheMap[cacheName];
    if (objectCache == null) {
      objectCache = new ObjectEchache(manager.getCache(cacheName));
      cacheMap[cacheName] = objectCache;
    }
    return objectCache;
  }



  def isConfiged() {
    String[] cacheNames = CacheManager.getInstance().getCacheNames()
    return cacheNames.any {
      it == 'vivamagCache'
    }
  }
}
