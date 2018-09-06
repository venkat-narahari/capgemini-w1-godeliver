package com.stackroute.recommendation.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.domain.Wishlist;

@RelationshipEntity(type = "likes")
public class Likes {
	
	@StartNode
	private User user;
	@EndNode
	private Wishlist Wishlist;
	public Likes() {
		super();
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Wishlist getWishlist() {
		return Wishlist;
	}
	public void setWishlist(Wishlist wishlist) {
		this.Wishlist = wishlist;
	}
	public Likes(User user, Wishlist wishlist) {
		super();
		this.user = user;
		this.Wishlist = wishlist;
	}
	@Override
	public String toString() {
		return "Likes [user=" + user + ", wishlist=" + Wishlist + "]";
	}
	
	
}
