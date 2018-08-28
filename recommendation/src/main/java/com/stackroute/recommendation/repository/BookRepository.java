package com.stackroute.recommendation.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.domain.BookListener;

public interface BookRepository extends Neo4jRepository<BookListener, Long>{

	 @Query("MATCH (book:BookListener) WHERE book.rating>8 RETURN book")
	 public List<BookListener> getAllBooksByRating();
	

}
