package com.everis.payment.service;

import com.everis.payment.controller.Payment;
import com.everis.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return Mono.just(payment).flatMap(obj -> {
            obj.setId(UUID.randomUUID());
            obj.setCreateDate(new Date());

            return repository.save(payment);
        }).doOnNext(obj -> {
            log.info("Enviando a server Kafka " + 1);
        }).onErrorResume(throwable -> Mono.error(new Exception(throwable)));
    }
}
