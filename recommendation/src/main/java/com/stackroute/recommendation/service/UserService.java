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
	//LikesRepository likesRepository;
	BookListener bookObj;
	UserPreferencesRepository userPreferencesRepository;
	WishlistRepository wishlistRepository;
	LikedRepository likedRepository;
	Wishlist wishlist;

	@Autowired
	public UserService(UserRepository userRepo, UserPreferencesRepository userPreferencesRepository,
		LikedRepository likedRepository, WishlistRepository wishlistRepository) {
		this.userRepo = userRepo;
		//this.likesRepository = likesRepository;
		this.userPreferencesRepository = userPreferencesRepository;
		this.wishlistRepository = wishlistRepository;
		this.likedRepository = likedRepository;
	}

	@KafkaListener(topics = "userprofile", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	public void save(@Payload UserProfile userListener) {
		User userObj = new User(userListener.getUserName(), userListener.getUserEmail(), userListener.getUserDob(),
				userListener.getUserPassword(), userListener.getUserPreferences(), userListener.getUserGender(),
				userListener.getUserMobile(),userListener.getWishlist());
		userRepo.save(userObj);
		UserPreferences userPreferences = new UserPreferences(userListener.getUserPreferences());
		userPreferencesRepository.save(userPreferences);
		
		System.out.println("harithaaaaaaaaaaaaaaa");
//		
//		Wishlist wishlist1 = new Wishlist(wishlist.getNodeId(), wishlist.getAuthor(), wishlist.getBookISBN_10(),
//				wishlist.getCost(), wishlist.getEmailID(), wishlist.getGenre(), wishlist.getPoster(),
//				wishlist.getPublisher(), wishlist.getQuantity(), wishlist.getTitle());
//		System.out.println("hhhh" + wishlist1);
//		Liked liked =new Liked(userObj,wishlist1);
//		likedRepository.save(liked);

	}

	public List<BookListener> getBooksByPreferences(String userMail) throws BookNotFoundException {
		List<BookListener> getAllBooks = (List<BookListener>) userRepo.getBooksByPreferences(userMail);
		if (!(getAllBooks.isEmpty())) {
			return getAllBooks;
		} else
			throw new BookNotFoundException("No books found");
	}

	public List<Wishlist> getBooksByLikes() throws BookNotFoundException {
		List<Wishlist> getAllBooks = wishlistRepository.getBooksByLikes();
//		Wishlist wishlist1 = new Wishlist(wishlist.getNodeId(), wishlist.getAuthor(), wishlist.getBookISBN_10(),
//				wishlist.getCost(), wishlist.getEmailID(), wishlist.getGenre(), wishlist.getPoster(),
//				wishlist.getPublisher(), wishlist.getQuantity(), wishlist.getTitle());
//		System.out.println("hhhh" + wishlist1);
//		User userObj =new User(userRepo.);
//		Liked liked =new Liked(userObj,wishlist1);
//		likedRepository.save(liked);

		if (!(getAllBooks.isEmpty())) {
			return getAllBooks;
		} else
			throw new BookNotFoundException("No books found");
	}

	public Wishlist save(Wishlist wishlist) {
		Wishlist getBooks = wishlistRepository.save(wishlist);
		return getBooks;
	}

	public List<Wishlist> getBooksFromWishlist() throws BookNotFoundException {
		List<Wishlist> getBooks = (List<Wishlist>) wishlistRepository.findAll();

		return getBooks;
	}

}