package com.stackroute.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.exceptions.BookAlreadyExistsException;
import com.stackroute.bookservice.exceptions.BookNotFoundException;
import com.stackroute.bookservice.exceptions.MongoConnectionException;
import com.stackroute.bookservice.services.BookServices;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class BookController {

	private BookServices bookServiceImpl;

	@Autowired
	public BookController(BookServices bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

//	 Gets all the list of books 

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllBooks() throws BookNotFoundException {
		List<Book> bookList;
		try {
			bookList = bookServiceImpl.getAllBooks();
			return new ResponseEntity<List<Book>>(bookList, HttpStatus.ACCEPTED);
		} catch (MongoConnectionException e) {
			
		}
		return null;
		

	}

//	Add the details of a book

	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveBook(@RequestBody Book book) throws BookAlreadyExistsException {

		try {
			Book savedBook;
			try {
				if ((savedBook = bookServiceImpl.saveBook(book)) != null) {

					return new ResponseEntity<Book>(savedBook, HttpStatus.ACCEPTED);
				} else {
					throw new BookAlreadyExistsException("book already exists");
				}
			} catch (MongoConnectionException e) {
				return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
			}
		} catch (BookAlreadyExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}
		

	}

//  Deletes a book by id

	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteBook(@PathVariable String bookId) {
		try {
			if (bookServiceImpl.deleteBook(bookId)) {
				return new ResponseEntity<String>("Deleted", HttpStatus.NO_CONTENT);
			}
		} catch (MongoConnectionException e) {
			return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
		}

		return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);

	}

//  Gets the list of books by matching search term with the book title 

	@RequestMapping(value = "/{term}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getByAlphabet(@PathVariable("term") String searchTerm) throws BookNotFoundException {
		List<Book> booklistalpha;
		try {
			booklistalpha = bookServiceImpl.findBookByRegexpTitle(searchTerm);
		} catch (MongoConnectionException e) {
			return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<List<Book>>(booklistalpha, HttpStatus.ACCEPTED);
	}

//	Gets the details of a book by id

	@RequestMapping(value = "book/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getById(@PathVariable("id") String bookId) throws BookNotFoundException {
		Book booklistalpha;
		try {
			booklistalpha = bookServiceImpl.findBookById(bookId);
		} catch (MongoConnectionException e) {
			return new ResponseEntity<String>("Connection Failed", HttpStatus.GATEWAY_TIMEOUT);
		}
		return new ResponseEntity<Book>(booklistalpha, HttpStatus.ACCEPTED);
	}

}
