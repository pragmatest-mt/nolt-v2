package com.pragmatest.nolt.restaurants.messaging.handlers;

import com.pragmatest.nolt.restaurants.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.restaurants.service.ProcessOrdersService;
import com.pragmatest.nolt.restaurants.service.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@Component
public class OrderSubmittedHandler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProcessOrdersService processOrdersService;

    @KafkaListener(
            topics = "${order.submitted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderSubmittedEventKafkaListenerContainerFactory")
    public void listenOrderSubmitted(OrderSubmittedEvent orderSubmittedEvent) {
        System.out.println("Received order submitted event: " + orderSubmittedEvent);

        Order order = modelMapper.map(orderSubmittedEvent, Order.class);

        processOrdersService.submitOrder(order);
    }

}
