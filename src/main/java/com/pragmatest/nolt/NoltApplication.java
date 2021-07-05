package com.pragmatest.nolt;

import com.pragmatest.nolt.messaging.listeners.CreateOrderListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.listener.MessageListener;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NoltApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(NoltApplication.class, args);
	}

}