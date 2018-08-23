package com.stackroute.bookservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.bookservice.domain.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	
//	@Query("SELECT b FROM Book b WHERE b.bookName LIKE :bname%")
//	public List<Book> getByBookName(@Param("bname") String bname);

}
