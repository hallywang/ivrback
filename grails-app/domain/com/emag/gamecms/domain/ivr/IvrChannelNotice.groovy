package com.emag.gamecms.domain.ivr

class IvrChannelNotice {

  String channelCode  //渠道号，可以使用拨打号
  String serviceId
  String noticeUrl
  Integer status =1 // 0 失效,1 有效
  Date createTime = new Date()
  Date updateTime = new Date()

  def beforeInsert () {
    createTime = new Date()
  }

  def beforeUpdate () {
    updateTime = new Date()
  }

  static constraints = {
  }
  static mapping = {
    table 't_ivr_channel_notice'
    version false
  }
}
