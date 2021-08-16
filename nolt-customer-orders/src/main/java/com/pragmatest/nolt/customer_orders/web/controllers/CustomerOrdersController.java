package com.pragmatest.nolt.customer_orders.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerOrdersController {

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    String submit() {
        return "Submitted";
    }
}
