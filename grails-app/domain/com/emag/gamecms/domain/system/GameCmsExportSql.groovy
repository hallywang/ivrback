package com.emag.gamecms.domain.system

/**
 * 导出sql用，存储导出的sql语句和参数信息
 * select * from table a where a.id={1} and a.name={2} and a.col={3}
 */
class GameCmsExportSql {

  String content    //sql 本身，{1} 第一个参数，{2} 第二个参数
  String sqlDesc  //描述
  String paramA     //第一个参数值
  String paramB     //第二个参数值
  String paramC
  String paramD
  String paramE
  String paramF
  String paramG
  String paramH
  String paramI
  String paramJ
  Date updateTime = new Date()

  static constraints = {
    paramA(nullable: true)
    paramB(nullable: true)
    paramC(nullable: true)
    paramD(nullable: true)
    paramE(nullable: true)
    paramF(nullable: true)
    paramG(nullable: true)
    paramG(nullable: true)
    paramI(nullable: true)
    paramJ(nullable: true)
    sqlDesc(nullable:true)
  }
  static mapping = {
    columns {
      content type: 'text'
    }
  }
}
