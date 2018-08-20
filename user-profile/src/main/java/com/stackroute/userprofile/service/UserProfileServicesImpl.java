package com.stackroute.userprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.repository.UserProfileRepository;

@Service
@Primary
public class UserProfileServicesImpl implements UserProfileServices {

	private UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServicesImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public UserProfile saveUser(UserProfile userProfile) {

		List<UserProfile> userList = (List<UserProfile>) userProfileRepository.findAll();
		if (userList.contains(userProfile)) {
			return null;
		} else {
			UserProfile saveUser = userProfileRepository.save(userProfile);
			return saveUser;
		}
	}

	@Override
	public UserProfile updateUser(int userId, UserProfile userProfile) {
		Optional<UserProfile> presentUser = userProfileRepository.findById(userId);
		if (!presentUser.isPresent()) {
			return null;
		} else {
			userProfile.setUserId(userId);
			UserProfile updatedUser = userProfileRepository.save(userProfile);
			return updatedUser;
		}
	}

	@Override
	public UserProfile getUserById(int userId) {
	
		return null;
	}
}
