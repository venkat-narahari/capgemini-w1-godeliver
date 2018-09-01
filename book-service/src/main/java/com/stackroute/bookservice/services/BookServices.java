package com.stackroute.bookservice.services;

import java.util.List;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.exceptions.MongoConnectionException;

public interface BookServices {

	public Book saveBook(Book book) throws MongoConnectionException;

	public List<Book> getAllBooks() throws MongoConnectionException;

	public boolean deleteBook(String bookId) throws MongoConnectionException;

	public List<Book> findBookByRegexpTitle(String searchTerm) throws MongoConnectionException;
	
	public Book findBookById(String bookId) throws MongoConnectionException;

}
