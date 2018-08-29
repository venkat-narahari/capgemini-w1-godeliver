package com.stackroute.bookservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.stackroute.bookservice.services.BookServices;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class BookController {

	private BookServices bookServiceImpl;
	Logger logger = LoggerFactory.getLogger("BootstrapData");

	@Autowired
	public BookController(BookServices bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllBooks() {
		try {
			List<Book> bookList;
			if ((bookList = bookServiceImpl.getAllBooks()) != null) {
				logger.debug("debug");
				logger.error("error");
				logger.warn("warm");
				logger.info("info");
				logger.trace("trace");
				return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
			} else {
				throw new BookNotFoundException();
			}
		} catch (BookNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "save/book", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveBook(@RequestBody Book book) throws BookAlreadyExistsException {

		try {
			Book savedBook;
			if ((savedBook = bookServiceImpl.saveBook(book)) != null) {

				return new ResponseEntity<Book>(savedBook, HttpStatus.OK);
			} else {
				throw new BookAlreadyExistsException("book already exists");
			}
		} catch (BookAlreadyExistsException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "delete/book/{bookId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteBook(@PathVariable String bookId) throws BookNotFoundException {
		try {

			if ((bookServiceImpl.deleteBook(bookId)) != null) {
				return new ResponseEntity<String>("deleted", HttpStatus.OK);
			} else {
				throw new BookNotFoundException();
			}
		} catch (BookNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "update/book/{bookId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> updateBook(@PathVariable String bookId, @RequestBody Book book)
			throws BookNotFoundException {
		try {
			Book updatedBook;
			if ((updatedBook = bookServiceImpl.updateBook(bookId, book)) != null) {
				return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
			} else {
				throw new BookNotFoundException(bookId);
			}
		} catch (BookNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/book/{bookTitle}", method = RequestMethod.GET)
	public ResponseEntity<?> getByBookTitle(@PathVariable String bookTitle) {
		List<Book> list = bookServiceImpl.getByTitle(bookTitle);

		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);

	}
}
