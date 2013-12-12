package com.emag.gamecms.domain.system

/**
 * User domain class.
 */
class MagSysUser2 implements Serializable {
  static transients = ['pass']
  static hasMany = [authorities: MagSysRole]
  static belongsTo = MagSysRole

  String username
  String userRealName
  String passwd
  boolean enabled
  String mobile
  boolean checkSmsYn
  MagSysDept dept
  String email
  boolean emailShow
  String description
  String pass = '[secret]'

  static constraints = {
    username(nullable:false, blank: false, unique: true, size: 0..50)
    userRealName(nullable:false, blank: false, size: 0..50)
    passwd(nullable:false, blank: false, size: 0..50)
    mobile(nullable:true, blank: true, matches: "[0-9]{11}")
    email(nullable:true, blank: true, size: 0..50)
    description(nullable: true, blank: true, size: 0..256)
    dept(nullable:false)
    enabled()
    checkSmsYn()
    emailShow()
    pass()
  }

  static mapping = {
    table "game_cms_sys_user"
    //factory lazy: false
    //id generator: 'sequence', params: [sequence: 'game_cms_sys_user_seq']
  }

  String toString() {
    return username
  }
}
