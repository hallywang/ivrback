package com.emag.gamecms.domain.ivr

class IvrConfigData {

  String operateId
  String content
  String configType // 0 单一数据，1 多条轮询， 2 多条随机
  String paramA
  String serviceId // 业务id
  Integer status=1 // 0 失效，1 有效
  String comment; //备注
  static mapping = {
    table 't_ivr_config_data'
    version false
  }
  static constraints = {
    operateId size:1..10,nullable: false
    serviceId size:1..20,nullable: false
    content size:1..200
    status()
    comment size:1..200
    paramA blank: true,nullable: true
  }
}
