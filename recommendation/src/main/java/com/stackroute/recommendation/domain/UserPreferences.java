package com.stackroute.recommendation.domain;

import java.util.Arrays;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class UserPreferences {
	@Id @GeneratedValue
	private Long nodeId;
	private String[] userPreferences;
//	@Relationship(type = "likes", direction = Relationship.INCOMING)
//	private List<User> user;
	public UserPreferences() {
		
	}

	public UserPreferences(String[] userPreferences) {
		this.userPreferences = userPreferences;
	}

	@Override
	public String toString() {
		return "UserPreferences [userPreferences=" + Arrays.toString(userPreferences) + "]";
	}

	public String[] getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(String[] userPreferences) {
		this.userPreferences = userPreferences;
	}

	
}

