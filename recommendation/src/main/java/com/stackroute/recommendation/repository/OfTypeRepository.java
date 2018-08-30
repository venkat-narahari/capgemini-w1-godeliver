package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.relations.OfType;

public interface OfTypeRepository extends Neo4jRepository<OfType, Long>{

}
