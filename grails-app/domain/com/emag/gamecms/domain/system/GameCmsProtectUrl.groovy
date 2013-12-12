package com.emag.gamecms.domain.system

import com.vivame.util.TimeUtil

class GameCmsProtectUrl {

  String url    //要保护的url，填写系统中模板的路径 如 game3/index
  Integer byIp = 0 //通过ip保护 0， 不保护， 1 保护
  String allowReferer  //允许的referer开头，逗号分隔
  String blockReferer  //禁止的referer,逗号分隔
  String afterBlock  //禁止后的跳转url
  Boolean isAllowNull = true //是否允许空referer
  Integer urlStatus = 1 // 状态：0无效、1有效
  Date startTime = new Date()  //开始时间
  Date endTime = TimeUtil.parseDateByString("2099-01-01", "yyyy-MM-dd", 1)
  //结束时间

  static constraints = {
    url(nullable: false, blank: false, unique: true)
    byIp(nullable: false, max: 1, min: 0)
    allowReferer(nullable: true)
    blockReferer(nullable: true)
    afterBlock(nullable: true)
    urlStatus(nullable: false, max: 1, min: 0)
    startTime(nullable: true)
    endTime(nullable: true)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_protect_url_seq']

    columns {
      allowReferer type: 'text'
      blockReferer type: 'text'
    }
  }
}
