package com.pragmatest.nolt.service;

import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.service.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<Order> getOrders();

    Order submitOrder(Order order);
}
