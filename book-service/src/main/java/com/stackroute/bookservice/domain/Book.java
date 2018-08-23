package com.stackroute.bookservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
	@Id
	private int id;
	private String bookISBN_13;
	private String title;
	private String poster;
	private String rating;
	private String volume;
	private String author;
	private String publisher;
	private String genre;
	private String cost;
	private String publishedYear;
	

	
	public Book(int id, String bookISBN_13, String title, String poster, String rating, String volume, String author,
			String publisher, String genre, String cost, String publishedYear) {
		
		this.id = id;
		this.bookISBN_13 = bookISBN_13;
		this.title = title;
		this.poster = poster;
		this.rating = rating;
		this.volume = volume;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.cost = cost;
		this.publishedYear = publishedYear;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookISBN_13() {
		return bookISBN_13;
	}
	public void setBookISBN_13(String bookISBN_13) {
		this.bookISBN_13 = bookISBN_13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}
	
	
	
	

}
