package com.stackroute.bookservice.exceptions;

public class BookNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	String bookISBN_10;
	
	public BookNotFoundException() {
		
	}

	public BookNotFoundException(String bookISBN_10) {
		this.bookISBN_10=bookISBN_10;
	}
	
	public String toString(){
	     return "books not found";
	}

}
