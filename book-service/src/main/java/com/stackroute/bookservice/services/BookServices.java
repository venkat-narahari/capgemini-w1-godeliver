package com.stackroute.bookservice.services;

import java.util.List;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.bookservice.exceptions.BookAlreadyExistsException;
import com.stackroute.bookservice.exceptions.BookNotFoundException;

public interface BookServices {

	public Book saveBook(Book book) throws BookAlreadyExistsException;

	public List<Book> getAllBooks();

	public Book deleteBook(int bookId);

	public List<Book> getByTitle(String bookTitle);


	public Book getBookById(int bookId) throws BookNotFoundException;

	public Book updateBook(Book book);

}
