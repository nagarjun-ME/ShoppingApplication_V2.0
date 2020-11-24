package com.naga.spring.dbservice.service;

import com.naga.spring.dbservice.model.Payment;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Payment payment);

    Payment updatePayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(long paymentId);

    HttpStatus deletePayment(long id);
}
