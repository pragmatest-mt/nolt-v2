package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOrdersService {

    public String submitOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        return order.getId();
    }

    // TODO - 1. Implement getOrder(id) method that returns the hardcoded order currently being returned by the delegate.
}
