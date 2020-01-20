package com.microservices.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.UserRatings;
import com.microservices.moviecatalogservice.services.CatalogItemService;
import com.microservices.moviecatalogservice.services.UserRatingsService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	CatalogItemService catalogItemService;
	
	@Autowired
	UserRatingsService userRatingsService;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {				
		UserRatings ratings = userRatingsService.getUserRating(userId);
		return ratings.getRatings()
			 .stream().map(rating -> catalogItemService.getCatalogItem(rating))
			 .collect(Collectors.toList());
	}	

}






















/* Utilizando o WebClientBuilder em vez do RestTemplate para fazer chamadas.
 * 

@Autowired
private WebClient.Builder webClientBuilder;

Movie movie = webClientBuilder.build()
	.get()
	.uri("http://localhost:8082/movies/" + rating.getMovieId())
	.retrieve()
	.bodyToMono(Movie.class)
	.block();
*/
