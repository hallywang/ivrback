package com.emag.gamecms.domain.system

class GameCmsMailServer {
  String serverName // 邮件服务器地址
  Integer smtpPort  // 端口号
  String username // 用户名
  String password // 密码
  String localhost // 大部分邮件服务器有反垃圾邮件的机制，如不设置则默认为调用方的机器名，此时邮件服务器将会直接拒绝
  String subject //发送邮件时默认使用的主题
  String text //发送邮件时默认使用的内容
  static hasMany = [receivers: GameCmsMailReceiver]

  static constraints = {
    serverName(nullable: false, blank: false, size: 0..60)
    smtpPort(nullable: false)
    username(nullable: false, blank: false, size: 0..60)
    password(nullable: false, blank: false, size: 0..60)
    localhost(nullable: true, blank: true, size: 0..60)
    subject(nullable: true, blank: true, size: 0..60)
    text(nullable: true, blank: true, size: 0..60)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_mail_server_seq']
  }
}
