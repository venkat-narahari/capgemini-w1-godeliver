package com.stackroute.bookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.bookservice.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

	@Query("{bookISBN_10 : ?0}")
	public Book getByBookISBN_10(String bookISBN_10);

	public static Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

}
