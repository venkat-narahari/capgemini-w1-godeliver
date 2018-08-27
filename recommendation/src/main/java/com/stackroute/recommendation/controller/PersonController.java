package com.stackroute.recommendation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.recommendation.domain.Book;
import com.stackroute.recommendation.domain.Person;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.PersonRepository;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonController {

	PersonRepository personRepository;
	BookRepository bookRepository;

	@Autowired
	public PersonController(PersonRepository personRepository,BookRepository bookRepository) {
		super();
		this.personRepository = personRepository;
		this.bookRepository=bookRepository;
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	
	public List<Person> getAllPersons() {
List<Person> getAllpersons = new ArrayList<Person>();
getAllpersons = (List<Person>) personRepository.getAllPersons();
		

	return getAllpersons;
	}
	public List<Book> getAllBooks() {
		List<Book> getAllbooks = new ArrayList<Book>();
		getAllbooks= (List<Book>) bookRepository.findAll();
		
			return getAllbooks;
			}
}
