package com.naga.spring.paymentservice.controller;

import com.naga.spring.paymentservice.dto.PaymentDTO;
import com.naga.spring.paymentservice.mapper.PaymentMapper;
import com.naga.spring.paymentservice.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
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


    private PaymentMapper paymentMapper;

    @Value("${db.service.url}")
    private String dbBaseUrl;

    @GetMapping("/")
    public String defaultPaymentMessage() {
        return "Welcome to Payment System!!!";
    }

    @GetMapping("/{pId}")
    public ResponseEntity<PaymentDTO> getItemList(@PathVariable("pId") long pId) {

        log.info("Payment details id : {} retrieving", pId);
        return ResponseEntity.ok().body(restTemplate.getForEntity(dbBaseUrl + pId, PaymentDTO.class, 1).getBody());
    }


    @GetMapping("/all")
    public ResponseEntity<List<PaymentDTO>> getAllItems() {

        log.info("Retrieving all payments details");

        ResponseEntity<List<PaymentDTO>> paymentResponse;
        paymentResponse = webClientBuilder.build()
                .get()
                .uri(dbBaseUrl + "/all")
                .retrieve()
                .toEntityList(PaymentDTO.class)
                .block();

        return ResponseEntity.ok().body(paymentResponse.getBody());
    }

    @PostMapping("/add")
    public Mono<Payment> savePayment(@RequestBody Payment payment) {

        Mono<Payment> pay=WebClient.create(dbBaseUrl)
                .post()
                .uri("/add")
                .body(Mono.just(payment),Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);


        log.info("Saving  payments : {}", pay);
        return  pay;
    }

}
