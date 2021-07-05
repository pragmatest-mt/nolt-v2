package com.pragmatest.nolt.messaging.listeners;


import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class CreateOrderListener {

    public CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "local.orders.cmd.create-order.0", groupId = "nolt.orders.create-order.consumer-group")
    public void listenCreateOrder(String message) {
        System.out.println("Received Message in group 'foo': " + message);
        latch.countDown();
    }
}


