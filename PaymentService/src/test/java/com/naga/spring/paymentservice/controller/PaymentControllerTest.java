package com.naga.spring.paymentservice.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@WebMvcTest(PaymentRestController.class)
@ExtendWith(SpringExtension.class)
public class PaymentControllerTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${db.payment.service.url}")
    private String dbBaseUrl;

    
}
