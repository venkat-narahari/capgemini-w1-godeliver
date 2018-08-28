package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.domain.BookListener;

public interface BookRepository extends Neo4jRepository<BookListener, Long>{

	 
	

}
