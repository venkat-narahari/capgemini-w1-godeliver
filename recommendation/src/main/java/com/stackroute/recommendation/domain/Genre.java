package com.stackroute.recommendation.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Genre {
	@Id
	@GeneratedValue
	private Long nodeId;
	private String name;

	@Override
	public String toString() {
		return "Genre [name=" + name + "]";
	}

	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}