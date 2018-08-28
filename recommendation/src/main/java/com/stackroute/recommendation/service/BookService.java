package com.stackroute.recommendation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;

import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.PersonRepository;

@Service
public class BookService {
	List<Book> bookFromTopic = new ArrayList<>();
	PersonRepository personRepository;
	BookRepository bookRepository;
	Book bookObj;

	@Autowired
	public BookService(PersonRepository personRepository, BookRepository bookRepository) {
		this.personRepository = personRepository;
		this.bookRepository = bookRepository;
	}

	@KafkaListener(groupId = "books", topics = "book_details")
	public void getBookFromTopic(@Payload Book book) {
		
		this.bookFromTopic.add(book);
		this.bookObj = book;
		
		System.out.println(book.toString());
		//bookRepository.save(book);
	System.out.println("gskjagdhajdsghsgdashdjasgk"+bookRepository.save(book));
		

	}

	public Iterable<Book> getAllBooksFromDb() {
		// return bookRepository.findAll();
		return bookFromTopic;
	}

	
//	public Book saveBooks() {
//		Book bookSaved = bookRepository.save(bookObj);
//		return bookSaved;
//	}

}
