package com.stackroute.bookservice.exceptions;

import com.stackroute.bookservice.domain.Book;

public class BookAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public BookAlreadyExistsException(String message) {
		this.message=message;
	}

	@Override
	public String toString() {
		return "BookAlreadyExistsException [message=" + message + "]";
	}
	

}
