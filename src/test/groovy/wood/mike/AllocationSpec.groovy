package wood.mike

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AllocationSpec extends Specification implements DomainUnitTest<Allocation> {

    void "test unique start"() {
        given:
            def start = new Date().clearTime()
            def dep1 = 'dep1'
            def off1 = 'offer1'

            def dep2 = 'dep2'

            def nullDep = null

            new Allocation(start: start, departure: dep1, offer: off1).save(flush: true)
        when:
            def alloc2 = new Allocation(start: start, departure: dep2, offer: off1)
        then:
            alloc2.validate()
        when:
            def alloc3 = new Allocation(start: start, departure: dep1, offer: off1)
        then: 'expect this to fail because using exact same values as existing Allocation'
            !alloc3.save()
            println alloc3.errors
        when:
            def alloc4 = new Allocation( start: start, departure: nullDep, offer: off1 )
        then: "should this validate? it doesn't"
            alloc4.save()
    }
}
