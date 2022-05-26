package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.services.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrdersService {

    public String submitOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        return order.getId();
    }

    public Order getOrder(String orderId, String customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setMenuItemId("burger");
        orderItem.setNotes("extra lettuce");

        List<OrderItem> orderItems = List.of(orderItem);

        order.setOrderItems(orderItems);
        order.setId(orderId);
        return order;
    }
}
