package com.naga.spring.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PaymentDTO {

    private String description;
    private double amount;
    private Date paymentDate;
    private String itemId;
}
