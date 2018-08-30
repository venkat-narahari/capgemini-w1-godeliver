package com.stackroute.recommendation.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.stackroute.recommendation.domain.BookListener;
import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.repository.LikesRepository;
import com.stackroute.recommendation.repository.UserPreferencesRepository;
import com.stackroute.recommendation.repository.UserRepository;
import com.stackroute.userprofile.domain.UserProfile;

@Service
public class UserService {
	private UserRepository userRepo;
	LikesRepository likesRepository;
	BookListener bookObj;
	UserPreferencesRepository userPreferencesRepository;

	@Autowired
	public UserService(UserRepository userRepo, LikesRepository likesRepository,
			UserPreferencesRepository userPreferencesRepository) {
		this.userRepo = userRepo;
		this.likesRepository = likesRepository;
		this.userPreferencesRepository = userPreferencesRepository;
	}

	@KafkaListener(topics = "userprofile", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	public void save(@Payload UserProfile userListener) {
		User userObj = new User(userListener.getUserName(), userListener.getUserEmail(), userListener.getUserDob(),
				userListener.getUserPassword(), userListener.getUserPreferences(), userListener.getUserGender(),
				userListener.getUserMobile());
		userRepo.save(userObj);
//		UserPreferences userPreferences = new UserPreferences(userListener.getUserPreferences());
//		System.out.println(userPreferencesRepository.save(userPreferences));

	}

	public List<BookListener> getBooksByPreferences(String userMail) {
		List<BookListener> getAllBooks = (List<BookListener>) userRepo.getBooksByPreferences(userMail);
		return getAllBooks;
	}

}