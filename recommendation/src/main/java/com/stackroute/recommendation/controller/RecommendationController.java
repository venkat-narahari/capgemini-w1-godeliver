package com.stackroute.recommendation.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.bookservice.domain.Book;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Wishlist;
import com.stackroute.recommendation.exceptions.BookNotFoundException;
import com.stackroute.recommendation.exceptions.NoBooksFoundException;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.service.BookService;
import com.stackroute.recommendation.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class RecommendationController {

	BookService bookService;
	BookRepository bookRepository;
	UserService userService;

	@Autowired
	public RecommendationController(BookService bookService, UserService userService, BookRepository bookRepository) {
		this.bookService = bookService;
		this.bookRepository = bookRepository;
		this.userService = userService;
	};
	/*
	 * getAllBooksFromDatabase() method is used to get all books from database, Rest
	 * end point for this method will be "api/v1/books"
	 */

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksFromDatabase() throws NoBooksFoundException {

		List<Book> books;
		try {
			books = bookService.getAllBooksFromDb();
		} catch (NoBooksFoundException e) {
			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	/*
	 * getAllBooksByRating() method is used to get books based on rating Rest end
	 * point for this method will be "api/v1/rating"
	 */

	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public ResponseEntity<?> getAllBooksByRating() throws BookNotFoundException {
		List<BookListener> getAllBooksByRating;
		try {
			getAllBooksByRating = (List<BookListener>) bookService.getAllBooksByRating();
		} catch (BookNotFoundException e) {
			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<BookListener>>(getAllBooksByRating, HttpStatus.OK);
	}

	/*
	 * getBooksByGenre() method is used to get books based on genre Rest end point
	 * for this method will be "api/v1/genre/{name}"
	 */

	@GetMapping(value = "/genre/{name}")
	public ResponseEntity<?> getBooksByGenre(@PathVariable String name) throws BookNotFoundException {
		List<BookListener> getAllBooksByGenre = new ArrayList<BookListener>();
		try {
			getAllBooksByGenre = (List<BookListener>) bookService.getBooksByGenre(name);
		} catch (BookNotFoundException e) {

			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<BookListener>>(getAllBooksByGenre, HttpStatus.OK);
	}

	/*
	 * getBooksByAuthor() method is used to get books written by author Rest end
	 * point for this method will be "api/v1/author/{name}"
	 */
	@GetMapping(value = "/author/{name}")
	public ResponseEntity<?> getBooksByAuthor(@PathVariable String name) throws BookNotFoundException {
		List<BookListener> getAllBooksByAuthor = new ArrayList<BookListener>();
		try {
			getAllBooksByAuthor = (List<BookListener>) bookService.getBooksByAuthor(name);
		} catch (BookNotFoundException e) {

			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<BookListener>>(getAllBooksByAuthor, HttpStatus.OK);
	}

	/*
	 * getBooksByAuthor() method is used to get books based on preferences Rest end
	 * point for this method will be "api/v1/prefereces/{userMail}"
	 */
	@GetMapping(value = "/preferences")
	public ResponseEntity<?> getBooksByPreferences(@RequestParam String userMail) {
		List<BookListener> getAllBooksByPreferences = new ArrayList<BookListener>();
		try {
			getAllBooksByPreferences = (List<BookListener>) userService.getBooksByPreferences(userMail);
		} catch (BookNotFoundException e) {
			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<BookListener>>(getAllBooksByPreferences, HttpStatus.OK);

	}
	/*
	 * to save the  wishlist books rest end
	 * point for this method will be "api/v1/save"
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Wishlist wishlist) {
		System.out.println(wishlist.getCost());
		Wishlist save = userService.save(wishlist);

		return new ResponseEntity<Wishlist>(save, HttpStatus.ACCEPTED);

	}
	/*
	 * to get the  wishlist books rest end
	 * point for this method will be "api/v1/wishlist"
	 */
	@GetMapping(value = "/wishlist")
	public ResponseEntity<?> getBooksFromWishlist() {
		List<Wishlist> getBooksFromWishlist = new ArrayList<Wishlist>();
		try {
			getBooksFromWishlist = (List<Wishlist>) userService.getBooksFromWishlist();
			
		} catch (BookNotFoundException e) {
			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<Wishlist>>(getBooksFromWishlist, HttpStatus.OK);

	}

	@GetMapping(value = "/liked")
	public ResponseEntity<?> getBooksByLikes() {
		List<Wishlist> getBooksFromLikes = new ArrayList<Wishlist>();
		try {
			getBooksFromLikes = userService.getBooksByLikes();
		} catch (BookNotFoundException e) {
			String result = e.getMessage();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<List<Wishlist>>(getBooksFromLikes, HttpStatus.OK);

	}

}
