package com.stackroute.bookservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.config.KafkaConfiguration;
import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.repository.BookRepository;

@Service
public class BookServicesImpl implements BookServices {

	private BookRepository bookRepository;
	private KafkaConfiguration kafkaConfig;

	@Autowired
	public BookServicesImpl(BookRepository bookRepository, KafkaConfiguration kafkaConfig) {
		this.bookRepository = bookRepository;
		this.kafkaConfig = kafkaConfig;
	}

	String topic = kafkaConfig.getTopic();

	@Autowired
	private KafkaTemplate<String, Book> kafkaTemplate;

	/**
	 * Checks whether the book exists and if not exists, the book will be saved
	 **/
	@Override
	public Book saveBook(Book book) {
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
	public List<Book> getAllBooks() {
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
	public boolean deleteBook(String bookId) {
		if (bookRepository.deleteBy(bookId) == null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns list of books by matching the search term with title
	 **/
	@Override
	public List<Book> findBookByRegexpTitle(String searchTerm) {
		List<Book> list = bookRepository.findBookByRegexpTitle(searchTerm);
		return list;
	}

	/**
	 * Returns a book by id
	 **/
	@Override
	public Book findBookById(String bookId) {
		Book book = bookRepository.findByTheBooksBookISBN_10(bookId);
		return book;
	}

}
