package com.pragmatest.nolt.restaurants.messaging.handlers;

import com.pragmatest.nolt.restaurants.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.restaurants.service.ProcessOrdersService;
import com.pragmatest.nolt.restaurants.service.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class OrderSubmittedHandler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProcessOrdersService processOrdersService;

    @KafkaListener(
            topics = "${order.submitted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderAcceptedEventKafkaListenerContainerFactory")
    public void listenOrderSubmitted(OrderSubmittedEvent orderSubmittedEvent) {
        System.out.println("Received order submitted event: " + orderSubmittedEvent);

        Order order = modelMapper.map(orderSubmittedEvent, Order.class);

        processOrdersService.submitOrder(order);
    }

}
