package com.stackroute.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Person;
import com.stackroute.recommendation.domain.BookListener;
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
	public List<BookListener> getAllBooks() {
		List<BookListener> getAllBooks = (List<BookListener>) personRepository.getAllBooks();
		return getAllBooks;
	}

	@Override
	public List<BookListener> getAllBooksByRating() {
		List<BookListener> getAllBooksByRating = (List<BookListener>) personRepository.getAllBooksByRating();
		return getAllBooksByRating;
	}

}
