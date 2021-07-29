package com.pragmatest.nolt.customers.web.controllers.utils;

import com.pragmatest.nolt.customers.messaging.events.OrderSubmittedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSubmittedHandler {

    static OrderSubmittedEvent eventReceived = null;

    @KafkaListener(
            topics = "${order.submitted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderSubmittedEventKafkaListenerContainerFactory")
    public void handleOrderAccepted(OrderSubmittedEvent orderSubmittedEvent) {
        eventReceived = orderSubmittedEvent;
    }

    public OrderSubmittedEvent getEventReceived() {
        return eventReceived;
    }
}
