package com.naga.spring.paymentservice.controller;

import com.naga.spring.paymentservice.dto.PaymentDTO;
import com.naga.spring.paymentservice.model.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/naga/shop/pay")
public class PaymentRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${db.payment.service.url}")
    private String dbBaseUrl;

    @GetMapping("/")
    public String defaultPaymentMessage() {
        return "Welcome to Payment System!!!";
    }

    @HystrixCommand
    @GetMapping("/{pId}")
    public ResponseEntity<Payment> getItemList(@PathVariable("pId") long pId) {

        log.info("Payment details id : {} retrieving", pId);
        return ResponseEntity.ok().body(restTemplate.getForEntity(dbBaseUrl + pId, Payment.class, 1).getBody());
    }


    @HystrixCommand
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllItems() {

        log.info("Retrieving all payments details");

        ResponseEntity<List<Payment>> paymentResponse;
        paymentResponse = webClientBuilder.build()
                .get()
                .uri(dbBaseUrl + "/all")
                .retrieve()
                .toEntityList(Payment.class)
                .block();

        return ResponseEntity.ok().body(paymentResponse.getBody());
    }

    @HystrixCommand
    @PostMapping("/add")
    public Mono<Payment> savePayment(@RequestBody PaymentDTO payment) {

        log.info("Saving  payments : {}", payment);
        return webClientBuilder.build()
                .post()
                .uri(dbBaseUrl + "/add")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(payment), PaymentDTO.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }

    @HystrixCommand
    @DeleteMapping("/rmv/{payId}")
    public Mono<HttpStatus> clearPayment(@PathVariable("payId") long payId) {

        log.info("Deleting payment : {} from system.", payId);
        return webClientBuilder.build()
                .delete()
                .uri(dbBaseUrl + "/rmv/" + payId)
                .retrieve()
                .bodyToMono(HttpStatus.class);

    }

    @HystrixCommand
    @PutMapping("/savechg/{payId}")
    public Mono<Payment> updatePayment(@PathVariable("payId") long payId, @RequestBody PaymentDTO paymentDTO) {

        log.info("Update payment : {} to the system.", paymentDTO);
        return webClientBuilder.build()
                .put()
                .uri(dbBaseUrl + "/edit/" + payId)
                .body(Mono.just(paymentDTO), PaymentDTO.class)
                .retrieve()
                .bodyToMono(Payment.class);

    }

}
