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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentRestApi.class)
class PaymentRestApiTest {

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private PaymentDTOConverter converter;

    @Autowired
    private MockMvc mockMvc;


    private final  String baseUrl="/db/pay/";
    @Test
   void  getPaymentByIdTest() throws Exception
    {
        //given
        //PaymentDTO paymentDTO=new PaymentDTO(2, "Movie Shopping", 2.12, new Date(), "MV002");

        PaymentDTO paymentDTO=PaymentDTO.builder()
                .paymentId(1)
                .description("Movie Shopping")
                .amount(2.12)
                .paymentDate(new Date())
                .itemId("MV002")
                .build();

        doReturn(new Payment()).when(paymentService).getPaymentById(2);
        doReturn(paymentDTO).when(converter).convertEntityToDto(any(Payment.class));

        // when + then
        this.mockMvc.perform(get(baseUrl+"/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount",is(paymentDTO.getAmount())));

    }

    @Test
    void readAllPaymentsTest() throws Exception {
        // given
        PaymentDTO paymentDTO=PaymentDTO.builder()
                .paymentId(1)
                .description("Movie Shopping")
                .amount(2.12)
                .paymentDate(new Date())
                .itemId("MV002")
                .build();
        List<PaymentDTO> paymentDTOS = Arrays.asList(paymentDTO);

        doReturn(new ArrayList<>()).when(paymentService).getAllPayments();
        doReturn(paymentDTOS).when(converter).convertEntityToDtoList(any());

        // when + then
        this.mockMvc.perform(get(baseUrl+"/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemId", is(paymentDTO.getItemId())));
    }

}
