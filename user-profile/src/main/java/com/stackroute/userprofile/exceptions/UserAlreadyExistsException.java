package com.stackroute.userprofile.exceptions;

import com.stackroute.userprofile.domain.UserProfile;

public class UserAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserProfile userProfile;
	
	public UserAlreadyExistsException(UserProfile userProfile) {
		this.userProfile=userProfile;
	}
	public String toString(){
	     return userProfile + " already exists" ;
	}
}
