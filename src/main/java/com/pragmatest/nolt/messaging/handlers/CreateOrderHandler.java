package com.pragmatest.nolt.messaging.handlers;

import com.pragmatest.nolt.messaging.commands.CreateOrderCommand;
import com.pragmatest.nolt.messaging.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderHandler {

    @KafkaListener(
            topics = "${create.order.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "createCommandKafkaListenerContainerFactory")
    @SendTo("${order.created.topic}")
    public OrderCreatedEvent listenCreateOrder(CreateOrderCommand command) {
        System.out.println("Received Command in group: " + command);
        return new OrderCreatedEvent("ORDERID", command.getOrderName(), command.getMenuItemId());
    }
}


