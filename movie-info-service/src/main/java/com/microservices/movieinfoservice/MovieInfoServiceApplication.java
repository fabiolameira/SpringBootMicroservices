package com.microservices.movieinfoservice;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {
	
	@Bean
	public RestTemplate getRestTemplate() {
		
	    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	    Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("proxy.indra.es", 8080));
	    requestFactory.setProxy(proxy);
	    
	    return new RestTemplate(requestFactory);
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

}
