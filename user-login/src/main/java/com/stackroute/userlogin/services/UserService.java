package com.stackroute.userlogin.services;

import org.springframework.kafka.annotation.KafkaListener;

import com.stackroute.userlogin.domain.User;
import com.stackroute.userprofile.domain.UserProfile;

public interface UserService {
//	@KafkaListener(topics = "userprofile", groupId = "group_json")
	User save(UserProfile userProfile);

	User findByEmail(String userEmail);

}
