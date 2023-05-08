package com.example.consumercardsnotifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJms
@SpringBootApplication
@Slf4j
@EnableScheduling
public class ConsumerCardsnotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerCardsnotifierApplication.class, args);
	}

}
