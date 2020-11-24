package com.naga.spring.paymentservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private long paymentId;

    private String description;
    private double amount;
    private Date paymentDate;
    private String itemId;
}
