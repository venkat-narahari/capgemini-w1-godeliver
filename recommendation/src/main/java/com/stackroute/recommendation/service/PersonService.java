package com.stackroute.recommendation.service;

import java.util.List;

import com.stackroute.bookservice.domain.Person;
import com.stackroute.recommendation.domain.BookListener;

public interface PersonService {

	public List<Person> getAllPersons();

	public List<BookListener> getAllBooks();

	public List<BookListener> getAllBooksByRating();

	// public List<Book> getAllBooksByRating();
}
