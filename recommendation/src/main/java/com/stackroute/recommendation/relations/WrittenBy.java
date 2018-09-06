package com.stackroute.recommendation.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import com.stackroute.recommendation.domain.Author;
import com.stackroute.recommendation.domain.BookListener;

@RelationshipEntity(type="writtenBy")
public class WrittenBy {
@Id 
//	private Long nodeId;
	
	@StartNode
	private BookListener bookListener;
	@EndNode
	private Author author;
//	public Long getNodeId() {
//		return nodeId;
//	}
//	public void setNodeId(Long nodeId) {
//		this.nodeId = nodeId;
//	}
	public BookListener getBookListener() {
		return bookListener;
	}
	public void setBookListener(BookListener bookListener) {
		this.bookListener = bookListener;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public WrittenBy( BookListener bookListener, Author author) {
		super();
//		this.nodeId = nodeId;
		this.bookListener = bookListener;
		this.author = author;
	}
	public WrittenBy() {
		super();
		
	}
	@Override
	public String toString() {
		return "WrittenBy [ bookListener=" + bookListener + ", author=" + author + "]";
	}
	

}
