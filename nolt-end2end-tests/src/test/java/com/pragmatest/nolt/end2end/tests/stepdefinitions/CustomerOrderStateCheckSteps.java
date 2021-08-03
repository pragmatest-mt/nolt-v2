package com.pragmatest.nolt.end2end.tests.stepdefinitions;

import com.pragmatest.nolt.end2end.tests.common.models.GetCustomerOrderResponse;
import com.pragmatest.nolt.end2end.tests.common.services.CustomerOrderService;
import com.pragmatest.nolt.end2end.tests.common.state.Order;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CustomerOrderStateCheckSteps {

    @Autowired
    CustomerOrderService customerService;

    @Autowired
    Order order;

    @Then("the customer's order is accepted")
    public void theCustomerSOrderIsAccepted() {

        ResponseEntity<GetCustomerOrderResponse> customerOrderResponseEntity = customerService.GetCustomerOrder("1", "1");

    }

}
