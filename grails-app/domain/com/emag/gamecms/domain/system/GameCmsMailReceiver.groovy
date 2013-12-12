package com.emag.gamecms.domain.system

class GameCmsMailReceiver {
  String mailAddress //邮箱地址
  static belongsTo = [mailServer: GameCmsMailServer]

  static constraints = {
    mailAddress(nullable: false, blank: false, size: 0..60)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_mail_receiver_seq']
  }

  public String toString() {
    return this.mailAddress
  }
}
