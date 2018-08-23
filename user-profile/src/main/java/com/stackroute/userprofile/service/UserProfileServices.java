package com.stackroute.userprofile.service;

import java.util.List;

import com.stackroute.userprofile.domain.UserProfile;

public interface UserProfileServices {

	public UserProfile saveUser(UserProfile userProfile);

	public UserProfile updateUser(UserProfile userProfile,String userEmail);

	public List<UserProfile> viewUser(String userEmail);


}
