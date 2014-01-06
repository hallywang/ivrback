package com.emag.gamecms.domain.city

class MobileInfo {

  String mobilePrefix
  Integer provinceId
  String provinceName
  Integer cityId
  String cityName
  Integer countyId
  String  countyName
  String rese0
  String rese1
  String rese2
  Date inDate
  String filename

  static constraints = {
    mobilePrefix(nullable:false,blank:false)
    provinceId(nullable:false,blank:false)
    provinceName(nullable:false,blank:false)
    cityId(nullable:false,blank:false)
    cityName(nullable:false,blank:false)
    countyId(nullable: true)
    countyName(nullable: true)
    rese0(nullable: true)
    rese1(nullable: true)
    rese2(nullable: true)
    inDate(nullable: true)
    filename(nullable: true)
  }

  static mapping = {
    table 't_mobile_info'
    version false
  }

}
