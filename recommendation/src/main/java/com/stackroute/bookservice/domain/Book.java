package com.stackroute.bookservice.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;

@NodeEntity
public class Book {
	
	@Id
	private String bookISBN_10;
	private String title;
	private String poster;
	private String rating;
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

	public Book(String bookISBN_10, String title, String poster, String rating, String volume, String author,
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

	@Override
	public String toString() {
		return "Book [bookISBN_10=" + bookISBN_10 + ", title=" + title + ", poster=" + poster + ", rating=" + rating
				+ ", volume=" + volume + ", author=" + author + ", publisher=" + publisher + ", genre=" + genre
				+ ", cost=" + cost + ", publishedYear=" + publishedYear + ", pages=" + pages + ", description="
				+ description + ", language=" + language + "]";
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
