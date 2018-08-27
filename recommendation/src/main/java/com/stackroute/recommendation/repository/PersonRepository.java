package com.stackroute.recommendation.repository;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.recommendation.domain.Person;
	
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {	
	
	@Query("MATCH (m:Book)-[r:ofType]->(a:Genre) RETURN m,a,r")
	
	//@Query("MATCH (book:Book) WHERE book.rating>8 RETURN book")
	public List<Person> getAllPersons();



//	Product findByTitle(@Param("title") String title);
//	Collection<Product> findByTitleLike(@Param("title") String title);

//    @Query("MATCH (user)-[l:likes]->(book)-[o:writtenBy]->(author)<-[w:writtenBy]-(books) RETURN books")
//	Collection<Product> graph(@Param("limit") int limit);
}
