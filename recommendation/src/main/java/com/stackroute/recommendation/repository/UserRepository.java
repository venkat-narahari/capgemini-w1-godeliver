package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.domain.User;

public interface UserRepository extends Neo4jRepository<User,Long>{

}
