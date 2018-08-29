package com.stackroute.recommendation.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@NodeEntity
public class Genre {
	@Id
	private Long nodeID;
	private String name;

	public Genre() {
		super();
		
	}

	public Genre(String name) {
	
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}

}