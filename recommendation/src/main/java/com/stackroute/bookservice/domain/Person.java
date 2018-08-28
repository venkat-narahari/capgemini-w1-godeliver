package com.stackroute.bookservice.domain;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.stackroute.recommendation.domain.BookListener;

@NodeEntity
public class Person {
	@Id
    @GeneratedValue
	private Long nodeID;
	
	
	private int id;
	private String name;
	private int age;
	private String gender;

	@Relationship(type = "likes", direction = Relationship.OUTGOING)
	List<BookListener> books;

	@Relationship(type = "likes", direction = Relationship.OUTGOING)
	List<Genre> genres;

	@Relationship(type = "rated", direction = Relationship.OUTGOING)
	List<BookListener> book;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int id, String name, int age, String gender, List<BookListener> books, List<Genre> genres, List<BookListener> book) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.books = books;
		this.genres = genres;
		this.book=book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<BookListener> getBooks() {
		return books;
	}

	public void setBooks(List<BookListener> books) {
		this.books = books;
	}
	public List<BookListener> getBook() {
		return books;
	}

	public void setBook(List<BookListener> book) {
		this.books = book;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", books=" + books
				+ ", genres=" + genres + "]";
	}

}
