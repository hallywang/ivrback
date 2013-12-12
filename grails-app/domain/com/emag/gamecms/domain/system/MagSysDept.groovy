package com.emag.gamecms.domain.system

/**
 * Created by IntelliJ IDEA.
 * User: lih
 * Date: 11-7-11
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
class MagSysDept  {
  String deptName
  String deptCode
  String description

  static constraints = {
    deptName(nullable:false, blank: false)
    deptCode(nullable:false, blank: false)
    description(nullable:true, blank: true)
  }

  static mapping = {
    table "game_cms_sys_dept"
    //id generator: 'sequence', params: [sequence: 'game_cms_sys_dept_seq']
  }

}
