package com.emag.gamecms.domain.city
/**
 * The City entity.
 *
 * @author
 *
 *
 */
class City {
  static mapping = {
    table 'city'
    // version is set to false, because this isn't available by default for legacy databases
    version false
    id generator: 'assigned', column: 'cityid'
  }

  Integer id
  Integer cityId
  String cityName

  static constraints = {
    id(nullable: false, max: 999999)
    cityId(max: 2147483647)
    cityName(nullable: true, blank: true, size: 0..20)
  }


}
