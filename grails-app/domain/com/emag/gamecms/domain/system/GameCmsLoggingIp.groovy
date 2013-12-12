package com.emag.gamecms.domain.system

/**
 * 管理后台安全相关
 * 记录可以登录管理后台的ip地址
 */
class GameCmsLoggingIp {
  String ip
  Integer status = 1     //状态：0无效，1有效，默认1
  Integer supportRegex = 0 //支持正则：0不支持，1支持，默认0，不支持正则时需要严格比对ip地址
  String description //描述

  static constraints = {
    ip(nullable: false, blank: false, size: 0..100)
    status(nullable: false, max: 1, min: 0)
    supportRegex(nullable: false, max: 1, min: 0)
    description(nullable: true, blank: true, size: 0..100)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_logging_ip_seq']
  }

  String toString() {
    return "ip:${ip},status:${status},supportRegex:${supportRegex}"
  }
}
