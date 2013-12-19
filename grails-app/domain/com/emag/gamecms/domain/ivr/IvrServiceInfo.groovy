package com.emag.gamecms.domain.ivr

class IvrServiceInfo {
  String serviceName
  String serviceId
  String serviceDesc
  String feeType  //计费类型，如 时长 秒计费，包月计费。
  Integer fee    //单位分
  String serviceType //如 互动类，收听类等
  Date startTime
  Date endTime
  Date createTime
  Date updateTime


  static mapping = {
    table 't_ivr_service_info'
    version false
  }

  static constraints = {
    serviceName size: 1..40, blank: false
    serviceId size: 1..40, blank: false
    serviceDesc size: 1..80, blank: true, nullable: true
    feeType size: 1..20, blank: true, nullable: true
    fee blank: true, nullable: true
    serviceType size: 1..20, blank: true, nullable: true
    startTime blank: true, nullable: true
    endTime blank: true, nullable: true

  }

  def beforeInsert() {
    createTime = new Date()
  }

  def beforeUpdate() {
    updateTime = new Date()
  }
}
