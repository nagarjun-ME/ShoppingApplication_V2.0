package com.naga.spring.dbservice.dto;

import lombok.*;

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


    public PaymentDTO(long paymentId, String description, double amount, Date paymentDate, String itemId) {
        this.paymentId = paymentId;
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.itemId = itemId;
    }

}
