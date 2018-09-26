package com.stackroute.bookservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.config.KafkaConfiguration;
import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.repository.BookRepository;
import com.stackroute.bookservice.exceptions.MongoConnectionException;

@Service
public class BookServicesImpl implements BookServices {

	private BookRepository bookRepository;
	private KafkaConfiguration kafkaConfig;

	@Autowired
	public BookServicesImpl(BookRepository bookRepository, KafkaConfiguration kafkaConfig) {
		this.bookRepository = bookRepository;
		this.kafkaConfig = kafkaConfig;
	}

	@SuppressWarnings("static-access")
	String topic = kafkaConfig.getTopic();

	// Kafka template from configuration and topic
	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;

	/**
	 * Checks whether the book exists and if not exists, the book will be saved
	 **/
	@Override
	public Book saveBook(Book book) throws MongoConnectionException {

		List<Book> bookList = (List<Book>) bookRepository.findAll();
		if (bookList.contains(book)) {
			return null;
		} else {

			Book savebook = bookRepository.save(book);
			kafkaTemplate.send(topic, book);
			return savebook;
		}
	}

	/** Gets all the list of books **/

	@Override
	public List<Book> getAllBooks() throws MongoConnectionException {

		List<Book> bookList = (List<Book>) bookRepository.findAll();
		if (bookList.isEmpty()) {
			return null;
		} else {
			return bookList;
		}
	}

	/**
	 * Checks whether the book exists if exists, the book will be deleted if not
	 * exists, return book not found
	 **/
	@Override
	public boolean deleteBook(String bookId) throws MongoConnectionException {

		if (bookRepository.deleteBy(bookId) == null) {
			return true;
		}
		return false;

	}

	/**
	 * Returns list of books by matching the search term with title
	 **/
	@Override
	public List<Book> findBookByRegexpTitle(String searchTerm) throws MongoConnectionException {
		List<Book> list = bookRepository.findBookByRegexpTitle(searchTerm);
		return list;
	}
	
	/**
	 * Returns list of books by matching the search term with genre
	 **/
	@Override
	public List<Book> findBookByRegexpGenre(String searchTerm) throws MongoConnectionException {
		List<Book> list = bookRepository.findBookByRegexpTitle(searchTerm);
		return list;
	}
	
	/**
	 * Returns list of books by matching the search term with author
	 **/
	@Override
	public List<Book> findBookByRegexpAuthor(String searchTerm) throws MongoConnectionException {
		List<Book> list = bookRepository.findBookByRegexpTitle(searchTerm);
		return list;
	}

	/**
	 * Returns a book by id
	 **/
	@Override
	public Book findBookById(String bookId) throws MongoConnectionException {
		Book book = bookRepository.findByTheBooksBookISBN_10(bookId);
		return book;
	}

}
