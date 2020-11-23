package com.naga.microservice.itemcatalog;

import com.naga.microservice.service.ItemFallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan("com.naga.microservice.api")
public class ItemCatalogApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){return new RestTemplate();}

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient(){ return  WebClient.builder();}

	@Bean
	public ItemFallbackService getItemFallbackService() {return new ItemFallbackService();}
	private static Logger log=LoggerFactory.getLogger(ItemCatalogApplication.class);
	
	public static void main(String[] args) {
		
		log.info("Main method beginning");
		SpringApplication.run(ItemCatalogApplication.class, args);
		
		log.info("Main method Ending");
	}

}
