package com.microservices.ratingsdataservice.models;

import java.util.List;

public class Ratings {
	
	private List<Rating> ratings;
	
	public Ratings() {
		super();
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}
