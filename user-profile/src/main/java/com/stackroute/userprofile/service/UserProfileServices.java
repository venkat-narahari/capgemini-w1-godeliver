package com.stackroute.userprofile.service;

import com.stackroute.userprofile.domain.UserProfile;

public interface UserProfileServices {

	public UserProfile saveUser(UserProfile userProfile);

	public UserProfile updateUser(int userId, UserProfile userProfile);

	public UserProfile getUserById(int userId);

}
