package com.stackroute.userprofile.service;

import java.util.List;

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

	/* 
	 * method for checking whether the user exists with the email id or not and if not exists, the user will be saved
	 */
	@Override
	public UserProfile saveUser(UserProfile user) {
		if (userProfileRepository.getByUserEmail(user.getUserEmail()).size() == 0) {
			user.setUserPassword(null);
			UserProfile savedUser = userProfileRepository.save(user);
			return savedUser;
		} else
			return null;
	}

	/* 
	 * method for updating the existing user
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
