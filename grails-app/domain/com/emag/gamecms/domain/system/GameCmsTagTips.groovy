package com.emag.gamecms.domain.system

/**
 * 页面标签使用提示
 */
class GameCmsTagTips {
  String tagName // 标签名称，如 适配链接
  String tagCode // 标签代码，如 <cms:link>
  String tagDesc
  String tagContent //标签中间内容,插入在<cms:entry>与</cms:entry>之间
  SortedSet tagParams
  static hasMany = [tagParams: GameCmsTagParams]   //所有参数

  static constraints = {
    tagName()
    tagCode()
    tagDesc(nullable: true)
    tagContent(nullable: true)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_tag_tips_seq']

    columns {
      tagDesc type: 'text'
      tagContent type: 'text'
    }
  }

  String toString() {
    return "$tagName,$tagCode"
  }
}
