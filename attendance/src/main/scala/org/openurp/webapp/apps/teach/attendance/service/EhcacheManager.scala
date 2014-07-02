package org.openurp.webapp.apps.teach.attendance.service

import org.beangle.commons.cache.{ Cache, CacheManager }
import net.sf.ehcache.{ Cache => EHCache, CacheManager => EHCacheManager, Element }
import org.beangle.commons.lang.ClassLoaders
/**
* EHCache 管理器
*
* @author chaostone
* @version 1.0, 2014/03/22
* @since 0.0.1
*/
class EhcacheManager extends CacheManager {

  val manager = EHCacheManager.getInstance()

  /**
* Return the cache associated with the given name.
*/
  def getCache[K, V](name: String): Cache[K, V] = {
    new EhCache(manager.getCache(name))
  }

  /**
* Return a collection of the caches known by this cache manager.
*/
  def cacheNames: Set[String] = {
    manager.getCacheNames().toSet
  }
}

class EhCache[K, V](val inner: EHCache) extends Cache[K, V] {
  /**
* Return the cache name.
*/
  def name(): String = inner.getName()

  /**
* Get Some(T) or None
*/
  def get(key: K): Option[V] = {
    val ele = inner.get(key)
    if (null == ele) None
    else Some(ele.getObjectValue.asInstanceOf[V])
  }

  /**
* Put a new Value
*/
  def put(key: K, value: V) {
    inner.put(new Element(key, value, 1))
  }

  /**
* Evict specified key
*/
  def evict(key: K) {
    inner.remove(key)
  }

  /**
* Return cached keys
*/
  def keys(): Set[K] = {
    import scala.collection.JavaConversions._
    inner.getKeys().toSet.asInstanceOf[Set[K]]
  }

  /**
* Remove all mappings from the cache.
*/
  def clear() {
    keys foreach { key =>
      evict(key)
    }
  }
}