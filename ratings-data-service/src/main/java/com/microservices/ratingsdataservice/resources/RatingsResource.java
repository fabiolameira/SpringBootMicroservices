package com.microservices.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingsdataservice.models.Rating;
import com.microservices.ratingsdataservice.models.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }
    
    @RequestMapping("users/{userId}")
    public UserRatings getUserMovieRatings(@PathVariable("userId") String userId) {
    	
    	List<Rating> ratings = Arrays.asList(
				new Rating("100", 4),
				new Rating("550", 3),
				new Rating("560", 5));
    	
    	UserRatings userRating = new UserRatings();
    	userRating.setUserId(userId);
    	userRating.setRatings(ratings);
    	
		return userRating;
    }

}