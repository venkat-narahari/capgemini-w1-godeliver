package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.stackroute.recommendation.relations.Likes;

public interface LikesRepository extends Neo4jRepository<Likes, Long> {

}
