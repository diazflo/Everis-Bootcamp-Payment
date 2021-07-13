package com.everis.payment.service;

import com.everis.payment.controller.Payment;
import com.everis.payment.kafka.kpayment.KafkaPayment;
import com.everis.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;
    private final KafkaTemplate kafkaTemplate;

    private KafkaPayment kafkaPayment;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, KafkaTemplate kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void init(){
        kafkaPayment = new KafkaPayment(kafkaTemplate);
    }

    @Override
    public Mono<Payment> createPayment(Payment payment) {
        return Mono.just(payment).flatMap(obj -> {
            obj.setId(UUID.randomUUID());
            obj.setCreateDate(new Date());

            return repository.save(payment);
        }).doOnNext(obj -> {
            log.info("Enviando a server Kafka " + 1);
            kafkaTemplate.send("t-send-payment", payment);
        }).onErrorResume(throwable -> Mono.error(new Exception(throwable)));
    }
}
