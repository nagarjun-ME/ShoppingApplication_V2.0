package com.naga.spring.paymentservice.mapper;

import com.naga.spring.paymentservice.dto.PaymentDTO;
import com.naga.spring.paymentservice.model.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    PaymentDTO toPaymentDTO(Payment payment);
    Payment toPayment(PaymentDTO paymentDTO);
    List<PaymentDTO> toPaymentDTOs(List<Payment> paymentList);
}
