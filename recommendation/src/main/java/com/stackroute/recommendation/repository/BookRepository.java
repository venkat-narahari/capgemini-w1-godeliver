package com.stackroute.recommendation.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.recommendation.domain.Book;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {	
//	@Query("MATCH (m:Book)-[r:ofType]->(a:Genre) RETURN m,a,r")
//	
//	//@Query("MATCH (book:Book) WHERE book.rating>8 RETURN book")
//	public List<Book> getAllBooks();

}
