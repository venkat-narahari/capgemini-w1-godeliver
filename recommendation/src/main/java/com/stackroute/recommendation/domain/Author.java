package com.stackroute.recommendation.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@NodeEntity
public class Author {
	@Id @GeneratedValue
	private Long nodeId;
	private String name;
	public Author(String name) {
		
		this.name = name;
	}
	@Override
	public String toString() {
		return "Author [ name=" + name + "]";
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
