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

	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		if (bookList.isEmpty()) {
			return null;
		} else {
			return bookList;
		}
	}

	@Override
	public List<Book> deleteBook(String bookTitle) {
		List<Book> findBook = bookRepository.getByBookTitle(bookTitle);
		bookRepository.deleteAll(findBook);
		return findBook;

	}

	@Override
	public List<Book> getByTitle(String bookTitle) {
		List<Book> list = bookRepository.getByBookTitle(bookTitle);
		return list;
	}
	@Override
	public List<Book> findBookByRegexpTitle(String searchTerm) {
		List<Book> list = bookRepository.findBookByRegexpTitle(searchTerm);
		return list;
	}

	

}
