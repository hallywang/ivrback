package com.emag.gamecms.domain.system

/**
 * Request Map domain class.
 */
class MagSysRequestmap implements Comparable {
  Integer status = 1 //状态：0显示，1显示
  Integer leaf = 0 //节点类型：0枝节点，1叶节点
  String url
  String configAttribute
  String realUrl //真实url,暂时作为controlname解
  String name
  SortedSet childs
  Integer orders = 0 //排序
  static belongsTo = [father: MagSysRequestmap]
  static def hasMany = [childs: MagSysRequestmap]
  static constraints = {
    realUrl(blank: true, maxSize: 100)
    father(nullable: true)

    status(nullable: false, max: 1, min: 0)
    leaf(nullable: false, max: 1, min: 0)
    url(nullable:false, blank: false, unique: true, maxSize: 100)
    configAttribute(nullable:false, blank: false)
    name(nullable:false, blank: false, maxSize: 50)
    orders(nullable: false)
    orders(unique: 'father')
  }

  static mapping = {
    father lazy: false
    table "game_cms_sys_request_map"
    //id generator: 'sequence', params: [sequence: 'game_cms_sys_request_map_seq']

    columns {
      configAttribute type: 'text'
    }
  }

  public String toString() {
    return "$id,$name,$url"
  }

  public int compareTo(Object o) {
    orders.compareTo(o.orders)
  }
}                                      
