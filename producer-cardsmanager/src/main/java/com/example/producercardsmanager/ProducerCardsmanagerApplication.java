package com.example.producercardsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class that can be used to bootstrap and launch a Spring application from a Java main method. By default class will perform the following steps to bootstrap your application:
 *  - Create an appropriate ApplicationContext instance (depending on your classpath)
 *  - Register a CommandLinePropertySource to expose command line arguments as Spring properties
 *  - Refresh the application context, loading all singleton beans
 *  - Trigger any CommandLineRunner beans
 */

@EnableScheduling
@EnableJms
@SpringBootApplication
public class ProducerCardsmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerCardsmanagerApplication.class, args);
	}

}
