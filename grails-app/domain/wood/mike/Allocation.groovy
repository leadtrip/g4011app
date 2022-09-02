package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false)
class Allocation {

    Date start
    String departure
    String offer

    static constraints = {
        departure nullable: true
        offer nullable: false
        //start(unique: ['departure', 'offer'])
        start nullable: false, validator: {value, object ->
            withNewSession {
                def alloc = Allocation.findByStartAndDepartureAndOffer(value, object.departure, object.offer)
                if( alloc && alloc.id != object.id ) {
                    return 'unique'
                }
            }
        }
    }
}
