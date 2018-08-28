package com.stackroute.recommendation.service;

import java.util.List;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.userprofile.domain.UserProfile;

public interface PersonService {

	public List<UserProfile> getAllPersons();

	public List<BookListener> getAllBooks();

	public List<BookListener> getAllBooksByRating();

	// public List<Book> getAllBooksByRating();
}
