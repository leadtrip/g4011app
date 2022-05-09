package wood.mike

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class BookServiceSpec extends Specification implements ServiceUnitTest<BookService>, DataTest{

    void setupSpec() {
        mockDomains Book
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test findBooks"() {
        given:
            String author = 'Dave'
            new Book(title: 'Mountains', author: author ).save()
            new Book(title: 'Rivers', author: author ).save()
            new Book(title: 'Lakes', author: author ).save()
            new Book(title: 'Clifs', author: author ).save()
            new Book(title: 'Clouds', author: 'Bob' ).save()
        when:
            def found = service.findBooks( author )
        then:
            found.size() == 4
    }
}
