package com.pragmatest.nolt.web.controllers;

import com.pragmatest.nolt.service.OrdersService;
import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.web.requests.OrderRequest;
import com.pragmatest.nolt.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    OrderResponse submit(OrderRequest orderRequest) {

       Order order = ordersService.submitOrder(null);
       OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

       return orderResponse;

    }


}
