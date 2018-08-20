 package com.stackroute.userprofile.exceptions;

public class UserProfileNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int userId;
	String uname;
	
	public UserProfileNotFoundException(int userId) {
		this.userId=userId;
	}
	
	public UserProfileNotFoundException(String uname) {
		this.uname=uname;
	}
	
	public String toString(){
	     return "user not found";
	}
	

}
