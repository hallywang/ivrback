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
  }
}
