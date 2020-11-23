package com.naga.spring.dbservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class DbServiceApplication {

	@Bean
	public ModelMapper getModelMapper(){return  new ModelMapper();}
	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
