package com.stackroute.recommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.repository.GenreRepository;

@Service
public class GenreService {
	GenreRepository genreRepo;
	Genre genre;
	Book book;

	@Autowired
	public GenreService(GenreRepository genreRepo) {
		super();
		this.genreRepo = genreRepo;
	}

//	@KafkaListener(groupId = "books", topics = "book_details", containerFactory = "kafkaListenerContainerFactoryq")
//	public void getGenreFromTopic(@Payload Book book) {
//		System.out.println("hhhhhhhh");
//		Genre genre = new Genre(book.getGenre());
//		System.out.println("hhhhhhhh" + book.getGenre());
//		genreRepo.save(genre);
//
//	}

	public void getGenreFromTopic() {
//		System.out.println("hhhhhhhh");
//		String s1 = BookService.bookFromTopic.get(0).getGenre();
//		System.out.println("genre from list is " + s1);
//		Genre genre = new Genre(s1);
//		System.out.println("hhhhhhhh" + genre);
//		Genre genre2 = genreRepo.save(genre);
//		System.out.println("saved successfully " + genre2);

	}

}
