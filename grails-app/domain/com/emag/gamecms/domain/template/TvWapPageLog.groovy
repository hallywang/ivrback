package com.emag.gamecms.domain.template

class TvWapPageLog {
  String name         //名称
  String content      //内容
  String head         //头
  String contentType  //http content type
  String description
  String title
  String link
  String published = '1' //是否发布的标志：0不发布，1已发布；默认为1
  static belongsTo = [template: TvWapPage]
  Date updateTime = new Date() //最后修改时间
  String userName //修改人名称

  static constraints = {
    name(nullable: false, blank: false, maxSize: 50)
    content(nullable: true, blank: true)
    head(nullable: true, blank: true)
    contentType(nullable: true, blank: true, size: 0..256)
    description(nullable: true, blank: true, size: 0..256)
    title(nullable: true, blank: true, size: 0..256)
    link(nullable: true, blank: true, size: 0..256)
    published(nullable:false, blank: false, maxSize: 2)
    updateTime(nullable: false)
    userName(nullable: false, blank: false)
  }

  static mapping = {
    table "game_cms_wap_page_log"
    //id generator: 'sequence', params: [sequence: 'game_cms_wap_page_log_seq']
    columns {
      content type: 'text'
      head type: 'text'
    }
  }

  String toString() {
    return name
  }
}

