package com.pragmatest.nolt.web.controllers;

import com.pragmatest.nolt.service.OrdersService;
import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/orders")
    List<OrderResponse> findAll() {

       List<Order> orders = ordersService.getOrders();

        Type responseType = new TypeToken<List<OrderResponse>>() {
        }.getType();

        List<OrderResponse> response = modelMapper.map(orders, responseType);

       return response;
    }


}
