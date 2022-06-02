package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOrdersService {

    public String submitOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        return order.getOrderId();
    }
}
