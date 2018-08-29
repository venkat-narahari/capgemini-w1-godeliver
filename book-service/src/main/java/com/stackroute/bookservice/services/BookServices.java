package com.stackroute.bookservice.services;

import java.util.List;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.exceptions.BookAlreadyExistsException;

public interface BookServices {

	public Book saveBook(Book book) throws BookAlreadyExistsException;

	public List<Book> getAllBooks();

	public String deleteBook(String bookId);

	public Book updateBook(String bookId, Book book);

	public List<Book> getByTitle(String bookTitle);


}
