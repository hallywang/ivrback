package com.emag.gamecms.domain.system

/**
 * Authority domain class.
 */
class MagSysRole implements Serializable {
  static hasMany = [people: MagSysUser2]
  String description
  String authority = 'ROLE_'
  MagSysDept dept

  static constraints = {
    authority(nullable:false, blank: false)
    description(nullable:true, blank: true, size: 0..256)
    dept(nullable: true)
  }

  static mapping = {
    table "game_cms_sys_role"
    //id generator: 'sequence', params: [sequence: 'game_cms_sys_role_seq']
  }
}
