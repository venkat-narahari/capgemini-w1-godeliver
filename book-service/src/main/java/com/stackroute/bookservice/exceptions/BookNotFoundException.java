package com.stackroute.bookservice.exceptions;

public class BookNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	String bookISBN_10;
	
	public BookNotFoundException() {
		
	}

	public BookNotFoundException(String bookId) {
		this.bookISBN_10=bookId;
	}
	
	public String toString(){
	     return "books not found";
	}

}
