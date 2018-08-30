package com.stackroute.recommendation.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.domain.User;

@RelationshipEntity(type = "likes")
public class Likes {
	
	@StartNode
	private User user;
	@EndNode
	private Genre genre;
	public Likes() {
		super();
		
	}
	@Override
	public String toString() {
		return "Likes [user=" + user + ", genre=" + genre + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
public Likes(User user, Genre genre) {
		
		this.user = user;
		this.genre = genre;
	}
}
