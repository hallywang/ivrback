package com.emag.gamecms.domain.actionlog


/**
 * 操作日志记录
 */
class GameCmsActionLog implements Serializable {
    private static final long serialVersionUID = 461673883024459725L

    String controllName  //操作名称，
    String actionName    // 如 编辑，删除等
    String actionId     //操作的对象id
    String userName //操作人的用户名
    Integer status = 0 //操作状态 0 成功 ，1 失败
    String ipAddress //ip地址
    Date actionTime = new Date()

    static constraints = {
        controllName(nullable: true, blank: true)
        actionName(nullable: true, blank: true)
        actionId(nullable: true, blank: true)
        ipAddress(nullable: true, blank: true)

        userName(nullable: false, blank: false)
        status(nullable: false, max: 1, min: 0)
        actionTime(nullable: false)
    }

//    static mapping = {
//        id generator: 'sequence', params: [sequence: 'game_cms_action_log_seq']
//    }
}
