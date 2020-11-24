package com.naga.spring.dbservice.restcontroller;

import com.naga.spring.dbservice.converter.PaymentDTOConverter;
import com.naga.spring.dbservice.dto.PaymentDTO;
import com.naga.spring.dbservice.model.Payment;
import com.naga.spring.dbservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentRestApi.class)
public class PaymentRestApiTest {

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private PaymentDTOConverter converter;

    @Autowired
    private MockMvc mockMvc;


    private final  String baseUrl="/db/pay/";

    @Test
    public void  getPaymentByIdTest() throws Exception
    {
        //given
        PaymentDTO paymentDTO=new PaymentDTO(2, "Movie Shopping", 2.12, new Date(), "MV002");

        doReturn(Optional.of(new Payment())).when(paymentService).getPaymentById(2);
        doReturn(paymentDTO).when(converter).convertEntityToDto(any(Payment.class));

        // when + then
        this.mockMvc.perform(get(baseUrl+"/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount",is(paymentDTO.getAmount())));

    }
}
