package com.stackroute.bookservice.exceptions;

public class BookNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int bookId;
	String bname;
	
	public BookNotFoundException() {
		
	}
	
	public BookNotFoundException(int bookId) {
		this.bookId=bookId;
	}
	
	public BookNotFoundException(String bname) {
		this.bname=bname;
	}
	
	public String toString(){
	     return "books not found";
	}

}
