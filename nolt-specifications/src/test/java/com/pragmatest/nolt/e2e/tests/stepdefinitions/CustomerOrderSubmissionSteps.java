package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.services.customers.CustomerOrderService;
import com.pragmatest.nolt.e2e.tests.common.state.Order;
import com.pragmatest.nolt.e2e.tests.common.state.OrderState;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.UUID;

public class CustomerOrderSubmissionSteps {

    @Autowired
    CustomerOrderService customerService;

    @Autowired
    Order order;

    @Given("a customer submits an order to a restaurant")
    public void aCustomerSubmitsAnOrderToARestaurant() {
    }

}
