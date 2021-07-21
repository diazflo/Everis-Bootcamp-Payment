package com.everis.payment;

import com.everis.payment.entity.Payment;
import com.everis.payment.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentApplicationTests {

	@Test
	void contextLoads() {
		Payment payment = Payment
				.builder()
				.amountPayment(BigDecimal.valueOf(120.00))
				.personRec(Person.builder().phoneNumber("998888502").build())
				.build();
		try {
			WebTestClient.bindToServer()
					.baseUrl("localhost:8087/")
					.build()
					.post()
					.uri("payment/createPayment")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.bodyValue(payment)
					.exchange()
					.expectStatus()
					.isOk();
		} catch (Exception e){
			e.printStackTrace();
		}


	}

}
