package com.microservices.ratingsdataservice.models;

import java.util.List;

public class UserRatings {
	
	private String userId;
	private List<Rating> ratings;
	
	public UserRatings() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}
