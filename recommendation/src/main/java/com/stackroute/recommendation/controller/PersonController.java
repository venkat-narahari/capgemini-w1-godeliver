package com.stackroute.recommendation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.domain.Person;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.PersonRepository;
import com.stackroute.recommendation.service.BookService;
import com.stackroute.recommendation.service.PersonService;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonController {

	PersonRepository personRepository;
	PersonService personService;
	BookService bookService;
	BookRepository bookRepository; 

	@Autowired
	public PersonController(PersonRepository personRepository, PersonService personService, BookService bookService,
			BookRepository bookRepository) {
		super();
		this.personRepository = personRepository;
		this.personService = personService;
		this.bookService = bookService;
		this.bookRepository = bookRepository;
	}

	@GetMapping(value = "/")
	public String hello() {
		return "Hi App is under development";
	}

	// @RequestMapping(value = "/persons", method = RequestMethod.GET)
	// public List<Person> getAllPersons() {
	// List<Person> getAllpersons = new ArrayList<Person>();
	// getAllpersons = (List<Person>) personService.getAllPersons();
	//
	// return getAllpersons;
	// }

	// @RequestMapping(value = "/books", method = RequestMethod.GET)
	// public List<Book> getAllBooks() {
	// List<Book> getAllbooks = new ArrayList<Book>();
	// getAllbooks = (List<Book>) personService.getAllBooks();
	//
	// return getAllbooks;
	// }

	@RequestMapping(value = "/getbooksfromdatabase", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksFromDatabase() {

		return new ResponseEntity<Iterable<Book>>(bookService.getAllBooksFromDb(), HttpStatus.OK);

	}
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<?> saveBook(){
	
		return new ResponseEntity<>(bookService.saveBooks(),HttpStatus.OK);
	}

	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	// public ResponseEntity<?> saveBook(@RequestBody Book book) {
	//
	// Book bookobj = bookService.saveBooks(book);
	//
	// return new ResponseEntity<Book>(bookobj, HttpStatus.OK);
	// }
	//
	// @RequestMapping(value = "/booksbyrating", method = RequestMethod.GET)
	// public List<Book> getAllBooksByRating() {
	// List<Book> getAllBooksByRating = new ArrayList<Book>();
	// getAllBooksByRating = (List<Book>) personService.getAllBooksByRating();
	//
	// return getAllBooksByRating;
	// }

}
