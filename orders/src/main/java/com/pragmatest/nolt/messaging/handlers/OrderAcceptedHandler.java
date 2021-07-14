package com.pragmatest.nolt.messaging.handlers;

import com.pragmatest.nolt.messaging.events.OrderAcceptedEvent;
import com.pragmatest.nolt.service.OrdersService;
import com.pragmatest.nolt.service.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderAcceptedHandler {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(
            topics = "${order.accepted.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderAcceptedEventKafkaListenerContainerFactory")
    public void handleOrderAccepted(OrderAcceptedEvent orderAcceptedEvent) throws Exception {
        Order order = modelMapper.map(orderAcceptedEvent, Order.class);
        ordersService.acceptOrder(order);
    }
}


