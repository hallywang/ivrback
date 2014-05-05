package com.emag.gamecms.domain.ivr

class IvrUserLogs {
  String msisdn
  String serviceId
  String callNumber
  Date callTime
  Date endTime
  Integer callSecond //持续了多少秒
  Long fee = 0L
  Date createTime
  String operateId
  String touchButton

  String provinceName //省份
  String cityName  //城市

  static constraints = {
    msisdn size:1..20,blank:false,nullable:false
    serviceId size:1..40,blank:true,nullable:true
    callNumber size:1..40, blank:true,nullable:true
    operateId size:1..255,blank: true,nullable: true
    touchButton size:1..600,blank: true,nullable: true
    provinceName size:1..10,blank: true,nullable: true
    cityName size:1..10,blank: true,nullable: true
  }

  static mapping = {
    table 't_ivr_user_logs'
    version false
  }
}
