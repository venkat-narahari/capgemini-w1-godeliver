package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.bookservice.domain.Book;

public interface BookRepository extends Neo4jRepository<Book, Long>{

}
