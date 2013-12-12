package com.emag.gamecms.domain.template

import com.emag.gamecms.domain.system.MagSysUser2

class TvWapTopic implements Serializable {
  String name
  String description
  Date createTime = new Date()
  MagSysUser2 creator
  static hasMany = [pages: TvWapPage]

  static constraints = {
    name(unique: true)
    creator(nullable:true)
  }

  static mapping = {
    // 避免用户删除以后，专题创建者无法找到而报错
    creator ignoreNotFound: true
    table "game_cms_wap_topic"
  }

  String toString() {
    return  this.name + ":" + this.description
  }
}
