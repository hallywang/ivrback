package com.emag.gamecms.domain.system

/**
 * 域名白名单，允许的referer
 */
class GameCmsReferer {
  String referer
  Date updateTime = new Date()
  Integer refererStatus = 1 //0 失效，1 生效
  Integer flag = 1 //保留字段，用于对referer分组
  String refererDesc

  static constraints = {
    referer(nullable: false, blank: false, size: 0..500)
    updateTime(nullable: false)
    refererStatus(nullable: false, max: 1, min: 0)
    flag(nullable: false, max: 1, min: 0)
    refererDesc(nullable: true, blank: true, size: 0..256)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_referer_seq']
  }
}
