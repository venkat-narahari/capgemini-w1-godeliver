package com.stackroute.recommendation.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.domain.User;

public interface UserRepository extends Neo4jRepository<User,Long>{
	@Query("MATCH (m:User)-[r:likes]->(a:Genre) where m.userName={userName} RETURN m")
	public List<Genre> getGenreLikedByUser(@Param("userName") String userName);
}
