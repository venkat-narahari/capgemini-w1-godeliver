package com.stackroute.bookservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
	
	@Id
	private String bookISBN_10;
	private String title;
	private String poster;
	private float rating;
	private String volume;
	private String author;
	private String publisher;
	private String genre;
	private String cost;
	private String publishedYear;
	private String pages;
	private String description;
	private String language;
	

	public Book() {
		
	}
	public Book(String bookISBN_10, String title, String poster, float rating, String volume, String author,
			String publisher, String genre, String cost, String publishedYear, String pages, String description,
			String language) {
		
		this.bookISBN_10 = bookISBN_10;
		this.title = title;
		this.poster = poster;
		this.rating = rating;
		this.volume = volume;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.cost = cost;
		this.publishedYear = publishedYear;
		this.pages = pages;
		this.description = description;
		this.language = language;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
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
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	} 
}
