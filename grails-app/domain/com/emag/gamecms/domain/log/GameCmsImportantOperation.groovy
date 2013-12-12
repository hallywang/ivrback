package com.emag.gamecms.domain.log

/**
 * 重要操作配置表
 * 记录哪些controller中的哪些action需要做日志记录
 */
class GameCmsImportantOperation {
  String controllerName
  String actionNames // 多个action之间使用逗号进行分割
  String domainName // 该 controller 操作的 domain 类名称，一般情况下应该与 controllerName 相同
  String description

  static constraints = {
    controllerName(nullable: false, blank: false)
    actionNames(nullable: false, blank: false)
    domainName(nullable: true, blank: true)
    description(nullable: true, blank: true)
  }

  static mapping = {
    version false
    //id generator: 'sequence', params: [sequence: 'game_cms_impt_op_seq']


    columns {
      description type: 'text'
    }
  }
}
