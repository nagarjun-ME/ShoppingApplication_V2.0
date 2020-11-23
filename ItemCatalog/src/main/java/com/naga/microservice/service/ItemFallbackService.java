package com.naga.microservice.service;

import com.naga.microservice.model.Item;
import com.naga.microservice.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemFallbackService {

    @Value("${db.service.url}")
    private String dbUri;

    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackItemFromProducts",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="3000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value ="5" ),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value ="50" ),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value ="10000" )
    })
    public Item getItemFromProducts(long pId) {

        log.info("Calling Microservice to retrieve products");
        return new Item("TEST001", "Test Items", 4, 12.22,
                Arrays.asList(restTemplate.getForEntity(dbUri + pId, Product.class, 1).getBody()));
    }


    private Item getFallbackItemFromProducts(long pId) {
        log.info("Returning default items . There is a problem while retrieving product with id : "+pId);
        return new Item( "Test000", "There are no Items", 0, 0, Arrays.asList(
                new Product(0, "SpringBoot ", "A beginner Guide", 0.0)
        ));
    }


    @HystrixCommand(fallbackMethod = "getFallbackForAllProductItems",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="5000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value ="10" ),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value ="60" ),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value ="15000" )
            })
    public List<Item> getAllProductItems() {
        ResponseEntity<Product[] > productResponse=restTemplate.getForEntity(dbUri+"/all", Product[].class);
        return Arrays.asList(new Item("001ABC", "Technology",2, 20.23, Arrays.asList(productResponse.getBody())));
    }

    private List<Item> getFallbackForAllProductItems() {
        return Arrays.asList( getFallbackItemFromProducts(0) );
    }
}
