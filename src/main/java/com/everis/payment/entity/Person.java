package com.everis.payment.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Person {
    private String phoneNumber;
    private String name;
}
