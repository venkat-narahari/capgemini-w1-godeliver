package com.stackroute.recommendation.service;

import java.util.List;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.domain.Person;

public interface PersonService {

	public List<Person> getAllPersons();

	public List<Book> getAllBooks();

	public List<Book> getAllBooksByRating();

	// public List<Book> getAllBooksByRating();
}
