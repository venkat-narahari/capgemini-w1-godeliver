package com.stackroute.recommendation.domain;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
public class Book {

	
	@Id
    @GeneratedValue
	private Long nodeId;
	private int id;

	private String title;
	private int rating;
	private int publishedYear;

	@Relationship(type = "writtenBy", direction = Relationship.OUTGOING)
	List<Author> authors;
	
	public Book() {
		
	}

	@Relationship(type = "ofType", direction = Relationship.OUTGOING)
	List<Genre> genre;




	public Book(int id, String title, int rating, int publishedYear, List<Author> authors, List<Genre> genre) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.publishedYear = publishedYear;
		this.authors = authors;
		this.genre = genre;
	}

	
	public Long getNodeId() {
		return nodeId;
	}

	public int getBookId() {
		return id;
	}

	public void setBookId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

}
