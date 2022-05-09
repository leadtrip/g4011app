package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class BookService {

    def findBooks( String author ) {
        Book.createCriteria().list {
            eq('author', author)
        }
    }
}
