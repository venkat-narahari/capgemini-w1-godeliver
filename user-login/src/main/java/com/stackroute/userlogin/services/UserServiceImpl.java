package com.stackroute.userlogin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stackroute.userlogin.domain.User;
import com.stackroute.userlogin.repository.UserRepository;
import com.stackroute.userprofile.domain.UserProfile;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userDao;
/* listening kafka from user-profile*/
	@KafkaListener(topics = "userprofile-login", groupId = "group-json")
	public User save(UserProfile userProfile) {
		User user = new User();
		user.setUserEmail(userProfile.getUserEmail());
		user.setUserPassword(userProfile.getUserPassword());
		return userDao.save(user);
	}
  
	public User findByEmail(String userEmail) {
		return userDao.findByUserEmail(userEmail);
	}

}
