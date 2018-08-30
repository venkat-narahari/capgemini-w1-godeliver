package com.stackroute.userprofile.service;

import com.stackroute.userprofile.domain.UserProfile;
import com.stackroute.userprofile.exceptions.MongoConnectionException;

public interface UserProfileServices {

	public UserProfile saveUser(UserProfile userProfile) throws MongoConnectionException;

	public UserProfile updateUser(UserProfile userProfile) throws MongoConnectionException;

	public UserProfile viewUser(String userEmail) throws MongoConnectionException;

}
