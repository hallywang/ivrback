package com.emag.gamecms.domain.system

/**
 * 标签提醒参数列表
 */
class GameCmsTagParams implements  Comparable{
  String paramName  //参数名
  String paramCode  //参数代码
  Boolean nullAble  //是否允许空 1 true 允许，0 false 不允许
  String defaultValue //不填时的默认值
  String paramDesc //描述
  Integer paramType //参数类型，1，分省展示；
  static belongsTo = [tagTip: GameCmsTagTips]

  static constraints = {
    paramName()
    paramCode(unique:'tagTip')
    nullAble()
    defaultValue(nullable: true)
    paramDesc(nullable: true)
    paramType(nullable: true)
  }

  static mapping = {
    //id generator: 'sequence', params: [sequence: 'game_cms_tag_params_seq']

    columns {
      paramDesc type: 'text'
    }
  }

  String toString() {
    return "$paramName,$paramCode"
  }

  int compareTo(Object obj) {
    paramCode.compareTo(obj.paramCode)
  }
}
