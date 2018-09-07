package com.stackroute.recommendation.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Wishlist {

	@Id
	@GeneratedValue
	private Long nodeId;
	private String author;
	private String bookISBN_10;
	private String cost;
	private String emailID;
	private String genre;
	private String poster;
	private String publisher;
	private int quantity;
	private String title;
	


	public Wishlist(Long nodeId, String author, String bookISBN_10, String cost, String emailID, String genre,
			String poster, String publisher, int quantity, String title) {
		super();
		this.nodeId = nodeId;
		this.author = author;
		this.bookISBN_10 = bookISBN_10;
		this.cost = cost;
		this.emailID = emailID;
		this.genre = genre;
		this.poster = poster;
		this.publisher = publisher;
		this.quantity = quantity;
		this.title = title;
		
	}

	public Wishlist(Iterable<Wishlist> iterable) {

	}

	public Wishlist() {
		
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookISBN_10() {
		return bookISBN_10;
	}

	public void setBookISBN_10(String bookISBN_10) {
		this.bookISBN_10 = bookISBN_10;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Wishlist [nodeId=" + nodeId + ", author=" + author + ", bookISBN_10=" + bookISBN_10 + ", cost=" + cost
				+ ", emailID=" + emailID + ", genre=" + genre + ", poster=" + poster + ", publisher=" + publisher
				+ ", quantity=" + quantity + ", title=" + title + "]";
	}



}
