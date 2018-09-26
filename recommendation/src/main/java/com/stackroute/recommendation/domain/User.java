package com.stackroute.recommendation.domain;

import java.util.Arrays;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {

	@Id
	@GeneratedValue
	private Long nodeId;
	private String userName;
	private String userEmail;
	private String userDob;
	private String userPassword;
	private String[] userPreferences;
	private String userGender;
	private double userMobile;

	@Relationship(type = "liked", direction = Relationship.OUTGOING)
	private List<Wishlist> wishlist;

	public User(String userName, String userEmail, String userDob, String userPassword, String[] userPreferences,
			String userGender, double userMobile, List<Wishlist> wishlist) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userDob = userDob;
		this.userPassword = userPassword;
		this.userPreferences = userPreferences;
		this.userGender = userGender;
		this.userMobile = userMobile;
		this.wishlist = wishlist;
	}

	public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
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

	public User() {

	}

	@Override
	public String toString() {
		return "User [nodeId=" + nodeId + ", userName=" + userName + ", userEmail=" + userEmail + ", userDob=" + userDob
				+ ", userPassword=" + userPassword + ", userPreferences=" + Arrays.toString(userPreferences)
				+ ", userGender=" + userGender + ", userMobile=" + userMobile + "]";
	}
}
