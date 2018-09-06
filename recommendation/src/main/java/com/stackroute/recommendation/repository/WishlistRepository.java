package com.stackroute.recommendation.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.recommendation.domain.Wishlist;

@Repository
public interface WishlistRepository extends Neo4jRepository<Wishlist, Long> {

	@Query("MATCH (p:User)-[l:liked]->(g:Wishlist) RETURN g")
	public List<Wishlist> getBooksByLikes();

}
