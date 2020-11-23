package com.naga.spring.dbservice.dto;

import lombok.*;

import java.util.Date;


public class PaymentDTO {

    private long paymentId;
    private String description;
    private double amount;
    private Date paymentDate;
    private String itemId;

    public PaymentDTO() {
    }

    public PaymentDTO(long paymentId, String description, double amount, Date paymentDate, String itemId) {
        this.paymentId = paymentId;
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.itemId = itemId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
