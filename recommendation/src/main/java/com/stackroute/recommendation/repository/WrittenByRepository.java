package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.relations.WrittenBy;

public interface WrittenByRepository extends Neo4jRepository<WrittenBy, Long> {

}
