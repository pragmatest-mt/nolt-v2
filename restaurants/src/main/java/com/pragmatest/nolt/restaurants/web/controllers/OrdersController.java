package com.pragmatest.nolt.restaurants.web.controllers;

import com.pragmatest.nolt.restaurants.service.ProcessOrdersService;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    ProcessOrdersService processOrdersService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/accept/{orderID}")
    OrderResponse accept(String orderId) {
        Order order = processOrdersService.acceptOrder(orderId);

        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

        return orderResponse;
    }
}