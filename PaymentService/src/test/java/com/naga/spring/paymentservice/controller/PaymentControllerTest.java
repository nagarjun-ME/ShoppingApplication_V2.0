package com.naga.spring.paymentservice.controller;

import com.naga.spring.paymentservice.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentRestController.class)
@ExtendWith(SpringExtension.class)
public class PaymentControllerTest {

    @MockBean
    private WebClient.Builder webClientBuilder;

    private final String dbBaseUrl = "${db.payment.service.url}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getItemList() throws Exception {
        Payment payment = Payment.builder()
                .paymentId(1).description("Movie Shopping").paymentDate(new Date()).itemId("TL001").build();
        doReturn(new Payment()).when(webClientBuilder).uriBuilderFactory();
        doReturn(paymentDTO).when(converter).convertEntityToDto(any(Payment.class));

        // when + then
        this.mockMvc.perform(get(baseUrl + "/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(paymentDTO.getAmount())));


    }
}
