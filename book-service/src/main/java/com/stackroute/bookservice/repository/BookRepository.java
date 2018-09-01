package com.stackroute.bookservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.bookservice.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

//	Query to get the book  by title

	@Query("{title : ?0}")
	public List<Book> getByBookTitle(String title);

//	Query to get the books by search term

	@Query("{'title':{$regex : ?0, $options:'i'}}")
	public List<Book> findBookByRegexpTitle(String regexp);

//	Query to get the book by bookISBN_10 id	

	@Query("{'_id' : ?0}")
	public Book findByTheBooksBookISBN_10(String bookISBN_10);

//	Query to delete the book by bookISBN_10 id

	@Query(value = "{'_id' : ?0}", delete = true)
	public Book deleteBy(String bookISBN_10);

}
