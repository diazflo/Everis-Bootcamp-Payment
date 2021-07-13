package com.everis.payment.service;

import com.everis.payment.controller.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<Payment> createPayment(Payment payment);
}
