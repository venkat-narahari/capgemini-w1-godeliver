package com.stackroute.orderdetails.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	private String bookISBN_10;
	private String title;
	private String cost;

	public Book() {

	}

	public Book(String bookISBN_10, String title, String cost) {
		super();
		this.bookISBN_10 = bookISBN_10;
		this.title = title;
		this.cost = cost;
	}

	public String getBookISBN_10() {
		return bookISBN_10;
	}

	public void setBookISBN_10(String bookISBN_10) {
		this.bookISBN_10 = bookISBN_10;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

}