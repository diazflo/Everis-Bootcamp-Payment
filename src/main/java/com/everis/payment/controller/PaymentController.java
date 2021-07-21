package com.everis.payment.controller;

import com.everis.payment.entity.Payment;
import com.everis.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/createPayment")
    public Mono<Payment> createPayment(@RequestBody Payment payment){
        log.info(payment.getPersonRec().getPhoneNumber());
        return service.createPayment(payment);
    }
}
