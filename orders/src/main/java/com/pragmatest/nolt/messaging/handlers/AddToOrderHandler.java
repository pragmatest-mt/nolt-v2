package com.pragmatest.nolt.messaging.handlers;

import com.pragmatest.nolt.messaging.commands.AddToOrderCommand;
import com.pragmatest.nolt.messaging.events.MenuItemAddedEvent;
import com.pragmatest.nolt.messaging.events.OrderCreatedEvent;
import com.pragmatest.nolt.messaging.producers.MenuItemAddedProducer;
import com.pragmatest.nolt.messaging.producers.OrderCreatedProducer;
import com.pragmatest.nolt.service.OrdersService;
import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.service.models.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddToOrderHandler {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderCreatedProducer orderCreatedProducer;

    @Autowired
    private MenuItemAddedProducer menuItemAddedProducer;

    @KafkaListener(
            topics = "${order.add-to-order.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "addToOrderCommandKafkaListenerContainerFactory")
    public void listenAddToOrder(AddToOrderCommand command) throws Exception {
        System.out.println("Received Command in group: " + command);

        Order createdOrder = ordersService.TryCreateOrder(command.getUserId());

        if (createdOrder != null) {
            orderCreatedProducer.send(new OrderCreatedEvent(command.getUserId(), createdOrder.getOrderId()));
        }

        OrderItem addedOrderItem = ordersService.TryAddMenuItem(command.getUserId(), command.getMenuItemId(), command.getQuantity());

        if (addedOrderItem != null) {
            menuItemAddedProducer.send(new MenuItemAddedEvent(addedOrderItem.getOrderId(), command.getUserId(), addedOrderItem.getMenuItemId(), addedOrderItem.getQuantity()));
        }
    }
}


