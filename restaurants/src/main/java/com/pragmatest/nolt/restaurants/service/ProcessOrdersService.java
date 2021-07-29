package com.pragmatest.nolt.restaurants.service;

import com.pragmatest.nolt.restaurants.service.models.Order;

import java.util.Date;

public interface ProcessOrdersService {

    Order acceptOrder(String orderId, Date estimatedReadyTime);

    void submitOrder(Order order);
}
