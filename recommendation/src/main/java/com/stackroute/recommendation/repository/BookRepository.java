package com.stackroute.recommendation.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.stackroute.recommendation.domain.BookListener;

public interface BookRepository extends Neo4jRepository<BookListener, Long> {

	@Query("MATCH (m:BookListener)-[r:ofType]->(a:Genre) RETURN m,r,a")
	public List<BookListener> getBooksByGenre();

	@Query("MATCH (m:BookListener) WHERE m.rating>5 RETURN m")
	public List<BookListener> getAllBooksByRating();

}
