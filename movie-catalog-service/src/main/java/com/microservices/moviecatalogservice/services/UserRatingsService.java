package com.microservices.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviecatalogservice.models.Rating;
import com.microservices.moviecatalogservice.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingsService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRatings getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRatings.class);
	}
	
	public UserRatings getFallbackUserRating(String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setUserId(userId);
		userRatings.setRatings(Arrays.asList(new Rating("0", 0)));
		
		return userRatings;
	}
}
