package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.domain.Author;

public interface AuthorRepository extends Neo4jRepository<Author, Long> {

}
