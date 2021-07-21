package com.everis.payment.repository;

import com.everis.payment.entity.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends ReactiveMongoRepository<Payment, UUID> {
}
