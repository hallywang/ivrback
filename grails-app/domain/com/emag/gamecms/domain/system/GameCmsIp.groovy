package com.emag.gamecms.domain.system


class GameCmsIp {
  String ip
  Integer flag = 1 //1南京wap网关ip地址
  String description
  Date createDate = new Date()
  Date updateDate

  static constraints = {
    ip(nullable: false, blank: false, size: 7..15)
    flag(nullable: true, max: 99, min: 0)
    description(nullable: true, blank: true, size: 0..500)
    createDate(nullable: false)
    updateDate(nullable: true)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_ip_seq']
  }
}
