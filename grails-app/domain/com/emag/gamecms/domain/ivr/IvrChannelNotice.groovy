package com.emag.gamecms.domain.ivr

class IvrChannelNotice {

  String channelCode  //渠道号，拨打号码的最后一位或多位
  String serviceId
  String noticeUrl
  String serviceClass //处理实现类
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
    serviceClass nullable: true
  }
  static mapping = {
    table 't_ivr_channel_notice'
    version false
  }
}
