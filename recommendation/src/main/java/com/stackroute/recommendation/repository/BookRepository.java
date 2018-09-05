package com.stackroute.recommendation.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.recommendation.domain.BookListener;

public interface BookRepository extends Neo4jRepository<BookListener, Long> {

	@Query("MATCH (m:BookListener)-[r:ofType]->(a:Genre) where a.name={name} RETURN m")
	public List<BookListener> getBooksByGenre(@Param("name") String name);

	@Query("MATCH (m:BookListener) WHERE m.rating>5 RETURN m")
	public List<BookListener> getAllBooksByRating();

	@Query("MATCH (u:BookListener)-[o:writtenBy]->(c:Author) where c.name={name} return u")
	public List<BookListener> getBookByAuthor(@Param("name") String name);

}
