package com.pragmatest.nolt.customers.service;

import com.pragmatest.nolt.customers.service.models.Order;

import java.util.List;

public interface OrdersService {
    List<Order> getOrders();

    Order submitOrder(Order order);

    Order acceptOrder(Order order);
}
