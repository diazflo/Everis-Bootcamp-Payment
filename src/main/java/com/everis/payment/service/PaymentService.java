package com.everis.payment.service;

import com.everis.payment.entity.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<Payment> createPayment(Payment payment);
}
