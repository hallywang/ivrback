package com.emag.gamecms.domain.ivr

class IvrBlackUser {


  Integer userType; //类型
  String msisdn
  String scope="0"; //0 全局；1 产品1,2 产品2，扩展。。。
  String comment; //备注
  Integer status=1; // 0  无效，1 有效
  Date createTime = new Date()
  Date updateTime = new Date()

  def beforeInsert () {
    createTime = new Date()
  }

  def beforeUpdate () {
    updateTime = new Date()
  }

  static mapping = {
    table 't_ivr_black_user'
    version false
  }

  static constraints = {
    msisdn size:1..20,blank:false,nullable:false,unique: 'scope'
    userType nullable:true
    scope blank:false,nullable:false
    status nullable:false
    comment size:1..40,blank:true,nullable:true


  }



}
