package com.stackroute.userprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.userprofile.config.KafkaConfiguration;
import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.MongoConnectionException;
import com.stackroute.userprofile.repository.UserProfileRepository;

@Service
@Primary
public class UserProfileServicesImpl implements UserProfileServices {

	private UserProfileRepository userProfileRepository;
	private KafkaConfiguration kafkaConfig;
	

	@Autowired
	public UserProfileServicesImpl(UserProfileRepository userProfileRepository,
			KafkaConfiguration kafkaConfig) {
		this.userProfileRepository = userProfileRepository;
		this.kafkaConfig = kafkaConfig;
		
	}

	@SuppressWarnings("static-access")
	String topic = kafkaConfig.getTopic();
	@SuppressWarnings("static-access")
	String topic1 = kafkaConfig.getTopic1();

	// Kafka template from configuration and topic
	@Autowired
	private KafkaTemplate<String, UserProfile> kafkaTemplate;

	/*
	 * Checks whether the user exists with the email id or not and if not exists,
	 * the user will be saved
	 */
	@Override
	public UserProfile saveUser(UserProfile user) throws MongoConnectionException {
		if (userProfileRepository.getByUserEmail(user.getUserEmail()) == null) {
			kafkaTemplate.send(topic, user);
			kafkaTemplate.send(topic1, user);
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
	public UserProfile updateUser(UserProfile user) throws MongoConnectionException {
		if (userProfileRepository.getByUserEmail(user.getUserEmail()) != null) {
			kafkaTemplate.send(topic, user);
			user.setUserPassword(null);
			UserProfile updatedUser = userProfileRepository.save(user);
			return updatedUser;
		}

		return null;
	}

	/*
	 * method for viewing the existing user
	 */
	@Override
	public UserProfile viewUser(String userEmail) throws MongoConnectionException {
		if (userProfileRepository.getByUserEmail(userEmail) != null) {
			UserProfile user = userProfileRepository.getByUserEmail(userEmail);
			return user;
		} else {
			return null;
		}
	}

}
