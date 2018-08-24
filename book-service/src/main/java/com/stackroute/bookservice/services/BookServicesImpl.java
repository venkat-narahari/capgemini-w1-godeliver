package com.stackroute.bookservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.repository.BookRepository;
import com.stackroute.bookservice.services.BookServices;

@Service
public class BookServicesImpl implements BookServices{
	
	private BookRepository bookRepository;

	@Autowired
	public BookServicesImpl(BookRepository bookRepository) {
		this.bookRepository=bookRepository;
	}

	@Override
	public Book saveBook(Book book) {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		if(bookList.contains(book)) {
			return null;
		}
		else {
			Book savebook = bookRepository.save(book);
			return savebook;
		}
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		if(bookList.isEmpty()) {
			return null;
		}
		else {
			return bookList;
		}
	}

	@Override
	public Book deleteBook(int bookId){
		Optional<Book> book = bookRepository.findById(bookId);
		if(!book.isPresent()) {
			return null;
		}
		else {
			bookRepository.deleteById(bookId);
			return book.get();
		}
		
	}

	@Override
	public Book updateBook(String bookId,Book book) {
		Book presentbook = bookRepository.getByBookISBN_10(bookId);
		if(presentbook==null) {
			return null;
		}
		else {
			book.setBookISBN_10(bookId);
			Book updatedbook = bookRepository.save(book);
			return updatedbook;
		}
	}

}
