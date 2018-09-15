package com.stackroute.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.domain.UserPreferences;
import com.stackroute.recommendation.domain.Wishlist;
import com.stackroute.recommendation.exceptions.BookNotFoundException;
import com.stackroute.recommendation.repository.LikedRepository;
import com.stackroute.recommendation.repository.UserPreferencesRepository;
import com.stackroute.recommendation.repository.UserRepository;
import com.stackroute.recommendation.repository.WishlistRepository;
import com.stackroute.userprofile.domain.UserProfile;

@Service
public class UserService {
	private UserRepository userRepo;

	BookListener bookObj;
	UserPreferencesRepository userPreferencesRepository;
	WishlistRepository wishlistRepository;
	LikedRepository likedRepository;
	Wishlist wishlist;

	@Autowired
	public UserService(UserRepository userRepo, UserPreferencesRepository userPreferencesRepository,
			LikedRepository likedRepository, WishlistRepository wishlistRepository) {
		this.userRepo = userRepo;
		this.userPreferencesRepository = userPreferencesRepository;
		this.wishlistRepository = wishlistRepository;
		this.likedRepository = likedRepository;
	}

	@KafkaListener(topics = "userprofile", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	public void save(@Payload UserProfile userListener) {
		User userObj = new User(userListener.getUserName(), userListener.getUserEmail(), userListener.getUserDob(),
				userListener.getUserPassword(), userListener.getUserPreferences(), userListener.getUserGender(),
				userListener.getUserMobile(), userListener.getWishlist());
		userRepo.save(userObj);
		UserPreferences userPreferences = new UserPreferences(userListener.getUserPreferences());
		userPreferencesRepository.save(userPreferences);
	}

	// to get the books by giving user email

	public List<BookListener> getBooksByPreferences(String userMail) throws BookNotFoundException {
		List<BookListener> getAllBooks = (List<BookListener>) userRepo.getBooksByPreferences(userMail);
		if (!(getAllBooks.isEmpty())) {
			return getAllBooks;
		} else
			throw new BookNotFoundException("No books found");
	}

	// to get the books when user likes the book

	public List<Wishlist> getBooksByLikes() throws BookNotFoundException {
		List<Wishlist> getAllBooks = wishlistRepository.getBooksByLikes();
		if (!(getAllBooks.isEmpty())) {
			return getAllBooks;
		} else
			throw new BookNotFoundException("No books found");
	}

	// to save the books in wishlist which are liked by user
	
	public Wishlist save(Wishlist wishlist) {
		Wishlist getBooks = wishlistRepository.save(wishlist);
		return getBooks;
	}

	// to get the books stored in wishlist
	public List<Wishlist> getBooksFromWishlist() throws BookNotFoundException {
		List<Wishlist> getBooks = (List<Wishlist>) wishlistRepository.findAll();

		return getBooks;
	}

}