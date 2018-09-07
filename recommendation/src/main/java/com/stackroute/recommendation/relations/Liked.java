package com.stackroute.recommendation.relations;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.stackroute.recommendation.domain.User;
import com.stackroute.recommendation.domain.Wishlist;


	@RelationshipEntity(type = "liked")
	public class Liked {
	@Id
		@StartNode
		private User user;
		@EndNode
		private Wishlist wishlist;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Wishlist getWishlist() {
			return wishlist;
		}

		public void setWishlist(Wishlist wishlist) {
			this.wishlist = wishlist;
		}

		public Liked(User user, Wishlist wishlist) {
			super();
			this.user = user;
			this.wishlist = wishlist;
		}

		public Liked() {

		}
}
