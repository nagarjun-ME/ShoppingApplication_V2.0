package com.naga.microservice.api;

import com.naga.microservice.model.Item;
import com.naga.microservice.model.Product;
import com.naga.microservice.service.ItemFallbackService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/naga/shop/item/")
public class ItemCatalogApi {

	private final Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	@Value("${db.service.url}")
	private String dbUri;

	@Autowired(required=true)
	private ItemFallbackService itemFallbackService;


	@GetMapping("/")
	public String sayHello()
	{
		log.info("Inside say hello");
		return "Welcome to item catalog";
	}
	
	//@HystrixCommand(fallbackMethod = "getFallbackItemList")
	@GetMapping("/{pId}")
	public ResponseEntity<Item> getItemList(@PathVariable("pId") long pId)
	{
		log.info("Item with product id  " + pId+ " retrieving");
		return ResponseEntity.ok().body(itemFallbackService.getItemFromProducts(pId));
	}



	//No need fallback method
	/*public ResponseEntity<Item> getFallbackItemList(@PathVariable("pId") long pId) {
		log.info("Returning default items . There is a problem while retrieving product with id : "+pId);
	 return ResponseEntity.ok().body(new Item( "Test002", "Technology", 0, 0, Arrays.asList(
	 		new Product(0, "Innovation in IOT", "A beginner Guide", 0.99)
	 )));
	}*/

	@GetMapping("/all")
	public ResponseEntity <List <Item>>  getAllItems()
	{
		log.info("Retrieving all Item details");
		return ResponseEntity.ok().body(itemFallbackService.getAllProductItems());
	}


	@HystrixCommand
	@DeleteMapping("/rmv/{prodId}")
	public ResponseEntity  <HttpStatus> deleteProductFromItemList(@PathVariable long prodId)
	{
		log.info("Deleting product "+prodId+" from Items");
		ResponseEntity<HttpStatus> responseEntity= webClient.baseUrl(dbUri)
				.build()
				.delete()
				.uri("/rmv/"+prodId)
				.retrieve()
				.toEntity(HttpStatus.class)
				.block();
		log.info("Deleted product "+prodId+" from Items" + responseEntity.getBody()) ;
		return responseEntity;
	}

}
