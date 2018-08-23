package com.stackroute.bookservice.services;

import java.util.List;

import com.stackroute.bookservice.domain.Book;

public interface BookServices {

	public Book saveBook(Book book);

	public List<Book> getAllBooks();

	public Book deleteBook(int bookId);

	public Book updateBook(int bookId, Book book);

//	public List<Book> getBookByName(String book);

}
