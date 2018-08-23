package com.stackroute.bookservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Book {
	@Id
	private int bookId;
	private String bookISBN_13;
	private String bookTitle;
	private String bookPoster;
	private String bookRating;
	private String bookVolume;
	private String bookWriter;
	private String bookPublisher;
	private String bookGenre;
	private String bookCost;
	
	public Book(int bookId, String bookISBN_13, String bookTitle, String bookPoster, String bookRating,
			String bookVolume, String bookWriter, String bookPublisher, String bookGenre, String bookCost) {
	
		this.bookId = bookId;
		this.bookISBN_13 = bookISBN_13;
		this.bookTitle = bookTitle;
		this.bookPoster = bookPoster;
		this.bookRating = bookRating;
		this.bookVolume = bookVolume;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.bookGenre = bookGenre;
		this.bookCost = bookCost;
	}
	
	
	public Book() {
	
	}


	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookISBN_13() {
		return bookISBN_13;
	}
	public void setBookISBN_13(String bookISBN_13) {
		this.bookISBN_13 = bookISBN_13;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookPoster() {
		return bookPoster;
	}
	public void setBookPoster(String bookPoster) {
		this.bookPoster = bookPoster;
	}
	public String getBookRating() {
		return bookRating;
	}
	public void setBookRating(String bookRating) {
		this.bookRating = bookRating;
	}
	public String getBookVolume() {
		return bookVolume;
	}
	public void setBookVolume(String bookVolume) {
		this.bookVolume = bookVolume;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookGenre() {
		return bookGenre;
	}
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	public String getBookCost() {
		return bookCost;
	}
	public void setBookCost(String bookCost) {
		this.bookCost = bookCost;
	}
	
	
	
	

	

}
