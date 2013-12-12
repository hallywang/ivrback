package com.emag.gamecms.domain.city
/**
 * The ProvCity entity.
 *
 * @author    
 *
 *
 */
class ProvCity {
    static mapping = {
         table 'prov_city'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         id generator:'assigned', column:'id'
         providProvince column:'provid'
         cityidCity column:'cityid'
    }

    // Relation
    Province providProvince
    // Relation
    City cityidCity

    static constraints = {
        providProvince()
        cityidCity()
    }

}
