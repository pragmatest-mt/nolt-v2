package com.pragmatest.nolt.restaurants.service;

import com.pragmatest.nolt.restaurants.service.models.Order;

public interface ProcessOrdersService {

    Order acceptOrder(String orderId);

    void submitOrder(Order order);
}
