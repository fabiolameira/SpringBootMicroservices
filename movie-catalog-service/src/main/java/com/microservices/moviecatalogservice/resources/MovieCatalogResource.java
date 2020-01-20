package com.microservices.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.Movie;
import com.microservices.moviecatalogservice.models.Rating;
import com.microservices.moviecatalogservice.models.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getCatalogFallback")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
				
		Ratings ratings = getUserRating(userId);
		
		 return ratings.getRatings()
				 .stream().map(rating -> getCatalogItem(rating))
				 .collect(Collectors.toList());
		
	}

	private CatalogItem getCatalogItem(Rating rating) {
		// Para cada MovieId, chamamos MovieInfoService e obtemos os detalhes
		 Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		 // Juntamos os dados do RatingDataService e do MovieInfoService
		 return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	private Ratings getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, Ratings.class);
	}
	
	public List<CatalogItem> getCatalogFallback(@PathVariable("userId") String userId) {
		return Arrays.asList(new CatalogItem("No movie", "No movies found.", 0));
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
