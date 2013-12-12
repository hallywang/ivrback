package com.emag.gamecms.domain.city
/**
 * The Province entity.
 *
 * @author
 *
 *
 */
class Province {
  static mapping = {
    table 'province'
    // version is set to false, because this isn't available by default for legacy databases
    version false
    id generator: 'assigned', column: 'provid'
  }

  Integer id
  Integer provId
  String provName

  static constraints = {
    id(nullable: false, max: 999999)
    provId(max: 2147483647)
    provName(nullable: true, blank: true, size: 0..20)
  }

}
