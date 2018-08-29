package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.domain.BookListener;

public interface BookRepository extends Neo4jRepository<BookListener, Long>{

//	 @Query("MATCH (book:BookListener) WHERE book.rating>8 RETURN book")
//	 public List<BookListener> getAllBooksByRating();
//	
//	 @Query("MATCH (m:BookListener) WHERE m.rating>'10' RETURN m")
//		public List<BookListener> getAllBooksByRating();

}
