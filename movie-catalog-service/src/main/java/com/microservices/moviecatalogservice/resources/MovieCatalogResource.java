package com.microservices.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.Movie;
import com.microservices.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
				
		List<Rating> ratings = Arrays.asList(
				new Rating("1001", 4),
				new Rating("1002", 3),
				new Rating("1003", 5));
		
		 return ratings.stream().map(rating -> {
			 
			 /* Utilizando o WebClientBuilder para fazer chamadas.
			 Movie movie = webClientBuilder.build()
			 	.get()
			 	.uri("http://localhost:8082/movies/" + rating.getMovieId())
			 	.retrieve()
			 	.bodyToMono(Movie.class)
			 	.block();
		 	*/
			 
			 // Utilizando o RestTemplate para fazer chamadas.
			 Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			 return new CatalogItem(movie.getName(), "Just an fast cars movie.", rating.getRating());
		 })
		 .collect(Collectors.toList());
		
		
	}

}
