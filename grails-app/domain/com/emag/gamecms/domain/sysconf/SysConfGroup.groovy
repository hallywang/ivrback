package com.emag.gamecms.domain.sysconf

/**
 * 挖宝系统配置分组表
 */
class SysConfGroup {

    String groupName  // 分组名称
    String remark    // 备注
    Date createTime = new Date()   // 新增时间
    Date updateTime   // 修改时间
    static hasMany = [conf: SysConf]

    static constraints = {
        createTime(nullable: true)
        updateTime(nullable: true)
    }

    static mapping = {
        table 'game_cms_sys_conf_group'
    }
}
