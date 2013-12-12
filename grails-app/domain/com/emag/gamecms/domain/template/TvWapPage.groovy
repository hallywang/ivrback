package com.emag.gamecms.domain.template

class TvWapPage implements Serializable {
  String name         //名称
  String content      //内容
  String head         //头
  String contentType  //http content type
  String description
  String title
  String link
  String published = '1' //是否发布的标志：0不发布，1已发布；默认为1
  TvWapTopic topic
  static hasMany = [logs: TvWapPageLog]

  static constraints = {
    name(nullable:false, blank: false, maxSize: 50)
    content(nullable: true, blank: true)
    head(nullable: true, blank: true)
    contentType(nullable: true, blank: true, size: 0..256)
    description(nullable: true, blank: true, size: 0..256)
    title(nullable: true, blank: true, size: 0..256)
    link(nullable: true, blank: true, size: 0..256)
    published(nullable:false, blank: false, maxSize: 2)
    topic(nullable: true)
  }

  static mapping = {
    table "game_cms_wap_page"
    //id generator: 'sequence', params: [sequence: 'game_cms_wap_page_seq']

    columns {
      content type: 'text'
      head type: 'text'
    }
  }

  String toString() {
    return topic.description + ',' + name + ',' + title
  }
}
