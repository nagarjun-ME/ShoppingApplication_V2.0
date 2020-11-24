package com.naga.spring.dbservice.converter;

import com.naga.spring.dbservice.dto.PaymentDTO;
import com.naga.spring.dbservice.model.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentDTOConverter {

    public PaymentDTO convertEntityToDto(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setDescription(payment.getDescription());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setItemId(payment.getItemId());
        return dto;
    }

    public List<PaymentDTO> convertEntityToDtoList(List<Payment> paymentList) {

        return paymentList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public Payment convertDtoToEntity(PaymentDTO paymentDTO) {
        Payment pojo = new Payment();
        pojo.setPaymentId(paymentDTO.getPaymentId());
        pojo.setDescription(paymentDTO.getDescription());
        pojo.setAmount(paymentDTO.getAmount());
        pojo.setPaymentDate(paymentDTO.getPaymentDate());
        pojo.setItemId(paymentDTO.getItemId());
        return pojo;
    }

    public List<Payment> convertDtoToEntityList(List<PaymentDTO> paymentDTOList) {
        return paymentDTOList.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }
}
