package com.stackroute.recommendation.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class UserPreferences {

	public UserPreferences() {
		
	}

	public UserPreferences(String[] userPreferences) {
		super();
		this.userPreferences = userPreferences;
	}

	public String[] getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(String[] userPreferences) {
		this.userPreferences = userPreferences;
	}

	private String[] userPreferences;
}
