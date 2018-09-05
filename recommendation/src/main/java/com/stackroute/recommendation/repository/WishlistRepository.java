package com.stackroute.recommendation.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.recommendation.domain.Wishlist;
@Repository
public interface WishlistRepository extends Neo4jRepository<Wishlist, Long>{
//	@Query("MATCH (u:User)-[l:likes]->(b:BookListener) return b")
//	public List<Wishlist> getBookFromWishlist();
}
