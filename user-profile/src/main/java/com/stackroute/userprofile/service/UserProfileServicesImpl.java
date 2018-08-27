package com.stackroute.userprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.userprofile.config.KafkaConfiguration;
import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;

@Service
@Primary
public class UserProfileServicesImpl implements UserProfileServices {

	private UserProfileRepository userProfileRepository;
	private KafkaConfiguration kafkaConfig;

	@Autowired
	public UserProfileServicesImpl(UserProfileRepository userProfileRepository, KafkaConfiguration kafkaConfig) {
		this.userProfileRepository = userProfileRepository;
		this.kafkaConfig=kafkaConfig;
	}
	
	  String topic = kafkaConfig.getTopic();

	    // Kafka template from configuration and topic
	    @Autowired
	    private KafkaTemplate<String, UserProfile> kafkaTemplate;

	/* 
	 * Checks whether the user exists with the email id or not and if not exists, the user will be saved
	 */
	@Override
	public UserProfile saveUser(UserProfile user) {
		if (userProfileRepository.getByUserEmail(user.getUserEmail()).size() == 0) {
			kafkaTemplate.send(topic, user);
			user.setUserPassword(null);
			UserProfile savedUser = userProfileRepository.save(user);
			return savedUser;
		} else
			return null;
	}

	/* 
	 * Updating the existing user
	 */
	@Override
	public UserProfile updateUser(UserProfile user, String userEmail) {
		user.setUserEmail(userEmail);
		UserProfile updatedUser = userProfileRepository.save(user);
		return updatedUser;
	}

	/* 
	 * method for viewing the existing user
	 */
	@Override
	public List<UserProfile> viewUser(String userEmail) {
		List<UserProfile> user = userProfileRepository.getByUserEmail(userEmail);
		return user;
	}

}
