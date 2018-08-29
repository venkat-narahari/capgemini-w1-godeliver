package com.stackroute.recommendation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.recommendation.domain.Author;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.repository.AuthorRepository;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.GenreRepository;

@Service
public class BookService {

	List<Book> bookFromTopic = new ArrayList<>();
	List<Book> booksByRating = new ArrayList<>();

	BookRepository bookRepository;
	GenreRepository genreRepository;
	AuthorRepository authorRepository;

	@Autowired
	public BookService(BookRepository bookRepository, GenreRepository genreRepository,AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.genreRepository = genreRepository;
		this.authorRepository=authorRepository;
	}

	/*
	 * getBookFromTopic() method is used to get books from book_details topic and it
	 * saves the books in Neo4j database.
	 */

	@KafkaListener(groupId = "books", topics = "book_details")
	public void getBookFromTopic(@Payload Book book) {
		System.out.println(book.toString());
		BookListener bookObj = new BookListener(book.getBookISBN_10(), book.getTitle(), book.getPoster(),
				book.getRating(), book.getVolume(), book.getAuthor(), book.getPublisher(), book.getGenre(),
				book.getCost(), book.getPublishedYear(), book.getPages(), book.getDescription(), book.getLanguage());
		bookRepository.save(bookObj);
		this.bookFromTopic.add(book);
		Genre genre = new Genre(book.getGenre());
		genreRepository.save(genre);
		Author author = new Author(book.getAuthor());
		authorRepository.save(author);
		
	}

	// getAllBooksFromDb() method is used to get books from book database

	public List<Book> getAllBooksFromDb() {
		return bookFromTopic;
	}

	/*
	 * getAllBooksByRating() method is used to get books from database based on
	 * rating
	 */
	public List<BookListener> getAllBooksByRating() {
		List<BookListener> getAllBooksByRating = (List<BookListener>) bookRepository.getAllBooksByRating();
		return getAllBooksByRating;
	}

	// getBooksByGenre() method is used to get books from database based on genre

	public List<BookListener> getBooksByGenre() {
		List<BookListener> getAllBooks = (List<BookListener>) bookRepository.getBooksByGenre();
		return getAllBooks;
	}

}
