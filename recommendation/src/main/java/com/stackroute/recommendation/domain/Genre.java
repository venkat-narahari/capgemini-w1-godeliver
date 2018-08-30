package com.stackroute.recommendation.domain;

import java.util.List;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Genre {
	@Id
	
	private String name;

	@Relationship(type = "ofType", direction = Relationship.INCOMING)
	private List<BookListener> books;
	

	public Genre() {
		super();
		
	}

	public Genre(String name) {
		super();
		
		this.name = name;
		
	}

	public Genre(String name, List<BookListener> books) {
		super();
		this.name = name;
		this.books = books;
	}

	public List<BookListener> getBooks() {
		return books;
	}

	public void setBooks(List<BookListener> books) {
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}
}