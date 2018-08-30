package com.stackroute.recommendation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.service.BookService;
import com.stackroute.recommendation.service.GenreService;
import com.stackroute.recommendation.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class Controller {

	BookService bookService;
	BookRepository bookRepository;
	GenreService genreService;
	UserService userService;

	@Autowired
	public Controller(BookService bookService, UserService userService,BookRepository bookRepository,GenreService genreService) {
		this.bookService = bookService;
		this.bookRepository = bookRepository;
		this.genreService=genreService;
		this.userService=userService;
	}
;
	/*
	 * getAllBooksFromDatabase() method is used to get all books from database,
	 * Rest end point for this method will be "api/v1/getbooksfromdatabase"
	 */
	
	@RequestMapping(value = "/getbooksfromdatabase", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksFromDatabase() {

		return new ResponseEntity<Iterable<Book>>(bookService.getAllBooksFromDb(), HttpStatus.OK);
	}
	
	
	
	/*
	 * getAllBooksByRating() method is used to get books based on rating
	 * Rest end point for this method will be "api/v1/booksbyrating"
	 */
	
	@RequestMapping(value = "/booksbyrating", method = RequestMethod.GET)
	public List<BookListener> getAllBooksByRating() {
		List<BookListener> getAllBooksByRating = new ArrayList<BookListener>();
		getAllBooksByRating = (List<BookListener>) bookService.getAllBooksByRating();
		return getAllBooksByRating;
	}

	/*
	 * getBooksByGenre() method is used to get books based on genre
	 * Rest end point for this method will be "api/v1/booksbygenre"
	 */
	
	@GetMapping(value="/getBooksByGenre/{name}")
	public List<BookListener> getBooksByGenre(@PathVariable String name) {
		List<BookListener> getAllBooksByGenre = new ArrayList<BookListener>();
		getAllBooksByGenre = (List<BookListener>) bookService.getBooksByGenre(name);
		return getAllBooksByGenre;
	}
	
	@GetMapping(value="/getBooksByAuthor/{name}")
	public  List<BookListener> getBooksByAuthor(@PathVariable String name) {
		List<BookListener> getAllBooksByGenre = new ArrayList<BookListener>();
		getAllBooksByGenre = (List<BookListener>) bookService.getBooksByAuthor(name);
			return getAllBooksByGenre;
		}
	@GetMapping(value="/genreLikedByUser/{userName}")
	public  List<Genre> getGenreLikedByUser(@PathVariable String userName) {
		List<Genre> getAllBooksByGenre = new ArrayList<Genre>();
		getAllBooksByGenre = (List<Genre>) userService.getGenreLikedByUser(userName);
		System.out.println("ffff"+getAllBooksByGenre);
			return getAllBooksByGenre;
		
	}


}
