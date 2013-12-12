package com.emag.gamecms.domain.sysconf

/**
 * 挖宝系统配置
 */
class SysConf {

    String sysconfKey  // 配置键
    String sysconfValue   // 值
    Integer status = 1   // 状态：0：无效，1：有效
    String remark   // 备注
    Date createTime = new Date() // 创建时间
    Date updateTime  // 修改时间
    static belongsTo = [group: SysConfGroup]  // 所属组

    static constraints = {
        createTime(nullable: true)
        updateTime(nullable: true)
        status(nullable: true)
        remark(nullable: true)
    }

    static mapping = {
        table 'game_cms_sys_conf'
    }
}
