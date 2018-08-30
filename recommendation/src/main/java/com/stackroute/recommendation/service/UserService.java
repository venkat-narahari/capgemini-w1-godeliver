package com.stackroute.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.Genre;
import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.repository.LikesRepository;
import com.stackroute.recommendation.repository.UserRepository;
import com.stackroute.userprofile.domain.UserProfile;

@Service
public class UserService {
	private UserRepository userRepo;
	LikesRepository likesRepository;
	BookListener bookObj;
	@Autowired
	public UserService(UserRepository userRepo, LikesRepository likesRepository) {
		this.userRepo = userRepo;
		this.likesRepository = likesRepository;
	}

	@KafkaListener(topics = "userprofile", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	public void save(@Payload UserProfile userListener) {
		User userObj = new User(userListener.getUserName(), userListener.getUserEmail(), userListener.getUserDob(),
				userListener.getUserPassword(), userListener.getUserPreferences(), userListener.getUserGender(),
				userListener.getUserMobile());
		userRepo.save(userObj);
		System.out.println("llllll"+userRepo.save(userObj));
		
		//UserPreferences userPreferences = new UserPreferences(userListener.getUserPreferences());
	
//		Genre genre = new Genre(bookObj.getGenre());
//		Likes likes = new Likes(userObj, genre);
//		System.out.println("llllsssssssssll"+likes);
//		likesRepository.save(likes);
		
	}
	public List<Genre> getGenreLikedByUser(String userName) {
		List<Genre> getAllGenres = (List<Genre>) userRepo.getGenreLikedByUser(userName);
		System.out.println("mmmmm"+getAllGenres);
		return getAllGenres;
	}

}