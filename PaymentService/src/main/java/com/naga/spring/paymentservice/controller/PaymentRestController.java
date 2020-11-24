package com.naga.spring.paymentservice.controller;

import com.naga.spring.paymentservice.dto.PaymentDTO;
import com.naga.spring.paymentservice.mapper.PaymentMapper;
import com.naga.spring.paymentservice.model.Payment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/naga/shop/pay")
public class PaymentRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private RestTemplate restTemplate;

    private WebClient.Builder webClientBuilder;


    private final PaymentMapper paymentMapper;

    @Value("${db.service.url}")
    private String dbBaseUrl;

    @GetMapping("/")
    public String defaultPaymentMessage() {
        return "Welcome to Payment System!!!";
    }

    @GetMapping("/{pId}")
    public ResponseEntity<PaymentDTO> getItemList(@PathVariable("pId") long pId) {

        log.info("Payment details id : {} retrieving", pId);

        return ResponseEntity.ok().body(paymentMapper.toPaymentDTO(restTemplate.getForEntity(dbBaseUrl + pId, Payment.class, 1).getBody()));
    }


    @GetMapping("/all")
    public ResponseEntity<List<PaymentDTO>> getAllItems() {

        log.info("Retrieving all payments details");

        // ResponseEntity <Payment[] > productResponse=restTemplate.getForEntity("http://shopping-db-service/db/products/all", Payment[].class);

        ResponseEntity<List<PaymentDTO>> paymentResponse;
        paymentResponse = webClientBuilder.build()
                .get()
                .uri(dbBaseUrl + "/all")
                .retrieve()
                .toEntityList(PaymentDTO.class)
                .block();

        return ResponseEntity.ok().body(paymentResponse.getBody());
    }


}
