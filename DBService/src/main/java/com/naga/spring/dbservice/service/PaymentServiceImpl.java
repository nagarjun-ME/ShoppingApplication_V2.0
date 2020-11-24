package com.naga.spring.dbservice.service;

import com.naga.spring.dbservice.exception.ProductNotFoundException;
import com.naga.spring.dbservice.jparepository.PaymentRepository;
import com.naga.spring.dbservice.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {

        Payment payment1 = getPaymentByPaymentId(payment.getPaymentId());


        payment1.setPaymentId(payment.getPaymentId());
        payment1.setDescription(payment.getDescription());
        payment1.setAmount(payment.getAmount());
        payment1.setPaymentDate(payment.getPaymentDate());
        payment1.setItemId(payment.getItemId());
        this.paymentRepository.save(payment1);
        return payment1;
    }

    @Override
    public List<Payment> getAllPayments() {
        return this.paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(long paymentId) {

        return getPaymentByPaymentId(paymentId);
    }

    @Override
    public HttpStatus deletePayment(long id) {

        log.info("Deleting Item : {} in DB ", id);
        Payment payment1 = getPaymentByPaymentId(id);
        if (payment1 == null) {
            log.info("Oops!! Item : {} not found in DB ", id);
            return HttpStatus.NOT_FOUND;
        } else {
            log.info("Item : {} found in DB. Deleting item.{}. ", id, payment1);
            this.paymentRepository.deleteById(id);
            return HttpStatus.ACCEPTED;
        }


    }

    private Payment getPaymentByPaymentId(long id) {
        Optional<Payment> p=this.paymentRepository.findById(id);
        if(p.isPresent()){return p.get();} else {return null;}

    }
}
