package com.stackroute.userprofile.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserProfile {
	private String userName;
	private String userEmail;
	private String userDob;
	private String userPassword;
	private String[] userPreferences;
	private String userGender;
	private double userMobile;

	public UserProfile() {

	}

	public UserProfile(String userName, String userEmail, String userDob, String userPassword, String[] userPreferences,
			String userGender, double userMobile) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userDob = userDob;
		this.userPassword = userPassword;
		this.userPreferences = userPreferences;
		this.userGender = userGender;
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserDob() {
		return userDob;
	}

	public void setUserDob(String userDob) {
		this.userDob = userDob;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String[] getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(String[] userPreferences) {
		this.userPreferences = userPreferences;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public double getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(double userMobile) {
		this.userMobile = userMobile;
	}

}
