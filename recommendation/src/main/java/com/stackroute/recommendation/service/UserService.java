package com.stackroute.recommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.repository.UserRepository;
import com.stackroute.userprofile.domain.UserProfile;

@Service
public class UserService {



private UserRepository userRepo;
@Autowired
public UserService(UserRepository userRepo) {
	this.userRepo=userRepo;
}

@KafkaListener(topics = "userprofile", groupId = "group_json",containerFactory="userKafkaListenerContainerFactory")
public void save(@Payload UserProfile userListener) {
	User userObj = new User(userListener.getUserName(),userListener.getUserEmail(),userListener.getUserDob(),userListener.getUserPassword(),userListener.getUserPreferences(),userListener.getUserGender(),userListener.getUserMobile());

System.out.println(userObj);
 userRepo.save(userObj);
}

//public User findByEmail(String userEmail) {
//	return userDao.findByUserEmail(userEmail);
//}
}