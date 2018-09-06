package com.stackroute.recommendation.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Genre;

@RelationshipEntity(type = "ofType")
public class OfType {
	@Id
	

	@StartNode
	private BookListener bookListener;
	@EndNode
	private Genre genre;
	
	

	public OfType() {
	}

	
	

	public OfType(BookListener bookListener, Genre genre) {
		super();
		this.bookListener = bookListener;
		this.genre = genre;
		
	}

	

	public BookListener getBookListener() {
		return bookListener;
	}

	public void setBookListener(BookListener bookListener) {
		this.bookListener = bookListener;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "OfType [bookListener=" + bookListener + ", genre=" + genre + "]";
	}

	
}
