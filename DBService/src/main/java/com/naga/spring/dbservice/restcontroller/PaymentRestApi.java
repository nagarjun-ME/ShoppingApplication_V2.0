package com.naga.spring.dbservice.restcontroller;

import com.naga.spring.dbservice.converter.PaymentDTOConverter;
import com.naga.spring.dbservice.dto.PaymentDTO;
import com.naga.spring.dbservice.model.Payment;
import com.naga.spring.dbservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db/pay")
public class PaymentRestApi {

    private Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaymentDTOConverter paymentDTOConverter;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public ResponseEntity<List<PaymentDTO>> readAllPayments()
    {
        log.info("Get all payments ");
        return ResponseEntity.ok().body(paymentDTOConverter.convertEntityToDtoList(paymentService.getAllPayments()));
    }

    @GetMapping("/{pId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("pId") long id)
    {
        log.info(" Finding payment with id :{}" ,id);

        return ResponseEntity.ok().body(paymentDTOConverter.convertEntityToDto(paymentService.getPaymentById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity < PaymentDTO> createPayment(@RequestBody PaymentDTO payment) {
        log.info(" Save payment with : {}" , payment);

        Payment payment1= paymentDTOConverter.convertDtoToEntity(payment);

        return ResponseEntity.ok().body(paymentDTOConverter.convertEntityToDto(paymentService.createPayment(payment1)));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity < PaymentDTO > updatePayment(@PathVariable long id, @RequestBody PaymentDTO paymentDto) {
        paymentDto.setPaymentId(id);
        log.info(" updating payment with id : {}", id);
        return ResponseEntity.ok().body(paymentDTOConverter.convertEntityToDto(paymentService.updatePayment(paymentDTOConverter.convertDtoToEntity(paymentDto))));
    }

    @DeleteMapping("/rmv/{id}")
    public HttpStatus deletePayment(@PathVariable long id) {
        log.info(" Deleting the payment with id : {}", id);
        paymentService.deletePayment(id);
        return HttpStatus.OK;
    }

}
