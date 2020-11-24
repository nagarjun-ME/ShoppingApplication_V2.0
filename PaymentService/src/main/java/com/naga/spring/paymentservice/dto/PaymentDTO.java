package com.naga.spring.paymentservice.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {

    private String description;
    private double amount;
    private Date paymentDate;
    private String itemId;
}
