package com.stackroute.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.domain.Person;
import com.stackroute.recommendation.repository.PersonRepository;

@Service("personimpl")
public class PersonServiceImpl implements PersonService {
	PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {

		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> getAllpersons = (List<Person>) personRepository.getAllPersons();
		return getAllpersons;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> getAllBooks = (List<Book>) personRepository.getAllBooks();
		return getAllBooks;
	}

	@Override
	public List<Book> getAllBooksByRating() {
		List<Book> getAllBooksByRating = (List<Book>) personRepository.getAllBooksByRating();
		return getAllBooksByRating;
	}

}
