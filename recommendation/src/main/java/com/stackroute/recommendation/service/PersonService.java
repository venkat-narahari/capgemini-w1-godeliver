package com.stackroute.recommendation.service;

import java.util.List;

import com.stackroute.recommendation.domain.BookListener;

public interface PersonService {

//	public List<UserProfile> getAllPersons();
//
	public List<BookListener> getBooksByGenre();
//
	public List<BookListener> getAllBooksByRating();

	// public List<Book> getAllBooksByRating();
}
