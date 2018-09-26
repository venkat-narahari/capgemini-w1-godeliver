package com.stackroute.recommendation.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.User;

public interface UserRepository extends Neo4jRepository<User,Long>{
	
	@Query("MATCH (p:User),(b:BookListener)-[:ofType]->(g:Genre) where p.userEmail={userEmail} AND g.name IN p.userPreferences RETURN b")
	public List<BookListener> getBooksByPreferences(@Param("userEmail") String userEmail);

	
	
	
		
}
