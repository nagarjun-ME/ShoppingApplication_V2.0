package com.naga.spring.paymentservice.model;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Payment{

    private long paymentId;
    private String description;
    private double amount;
    private Date paymentDate;
    private String itemId;

   }
