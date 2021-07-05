package com.pragmatest.nolt.messaging.listeners;

import com.pragmatest.nolt.messaging.commands.CreateOrderCommand;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderConsumer {

    public CreateOrderConsumer() {

    }

    @KafkaListener(
            topics = "local.orders.cmd.create-order.1",
            groupId = "nolt.orders.create-order.consumer-group",
            containerFactory = "createCommandKafkaListenerContainerFactory")
    public void listenCreateOrder(CreateOrderCommand command) {
        System.out.println("Received Command in group: " + command);
    }
}


