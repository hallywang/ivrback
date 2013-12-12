package com.emag.gamecms.domain.system


/**
 * 黑名单
 */
class BlackUser {
    String msisdn // 用户号码
    String description //描述
    Integer flag = 1 //
    Date createTime = new Date() //创建时间
    Date updateTime //更新时间

    static constraints = {
        msisdn(nullable: false, blank: false, size: 0..20)
        description(nullable: true, blank: true)
        flag(nullable: false, min: 1, max: 100)
        flag(unique: 'msisdn')
        createTime(nullable: false)
        updateTime(nullable: true)
    }

    static mapping = {
        //id generator: 'sequence', params: [sequence: 'black_user_seq']

        columns {
            description type: 'text'
        }
    }
}
