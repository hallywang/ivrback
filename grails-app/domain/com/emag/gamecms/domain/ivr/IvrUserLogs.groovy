package com.emag.gamecms.domain.ivr

class IvrUserLogs {
  String msisdn
  String serviceId
  String callNumber
  Date callTime
  Date endTime
  Integer callSecond //持续了多少秒
  Date createTime

  static constraints = {
    msisdn size:1..20,blank:false,nullable:false
    serviceId size:1..40,blank:true,nullable:true
    callNumber size:1..40, blank:true,nullable:true

  }

  static mapping = {
    table 't_ivr_user_logs'
    version false
  }
}
