package com.stackroute.recommendation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.repository.BookRepository;
import com.stackroute.recommendation.repository.PersonRepository;

@Service
public class BookService {
	List<Book> bookFromTopic = new ArrayList<>();
	PersonRepository personRepository;
	BookRepository bookRepository;


	@Autowired
	public BookService(PersonRepository personRepository, BookRepository bookRepository) {
		this.personRepository = personRepository;
		this.bookRepository = bookRepository;
	}


	@KafkaListener(groupId = "books", topics = "book_details")
	public void getBookFromTopic(@Payload Book book) {
		
		System.out.println(book.toString());
		BookListener bookObj=new BookListener(book.getBookISBN_10(),book.getTitle(),book.getPoster(),book.getRating(),book.getVolume(),book.getAuthor(),
				book.getPublisher(),book.getGenre(),book.getCost(),book.getPublishedYear(),book.getPages(),book.getDescription(),book.getLanguage());
		bookRepository.save(bookObj);
		this.bookFromTopic.add(book);

	}

	public List<Book> getAllBooksFromDb() {
		// return bookRepository.findAll();
		return bookFromTopic;
	}
	


}
