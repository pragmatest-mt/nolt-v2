package com.pragmatest.nolt.restaurants.web.controllers;

import com.pragmatest.nolt.restaurants.service.ProcessOrdersService;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrdersController {

    @Autowired
    ProcessOrdersService processOrdersService;

    @Autowired
    ModelMapper modelMapper;

    @PutMapping("/accept/{orderID}")
    OrderResponse accept(String orderId, @RequestBody Date estimatedReadyTime) {
        Order order = processOrdersService.acceptOrder(orderId, estimatedReadyTime);

        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

        return orderResponse;
    }
}