package com.stackroute.bookservice.exceptions;

import com.stackroute.bookservice.domain.Book;

public class BookAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Book book;
	
	public BookAlreadyExistsException(Book book) {
		this.book=book;
	}
	public String toString(){
	     return book + " already exists" ;
	}

}
