package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.contracts.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.e2e.tests.common.services.customers.CustomerOrderService;
import com.pragmatest.nolt.e2e.tests.common.state.Order;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CustomerOrderStateCheckSteps {

    @Autowired
    CustomerOrderService customerService;

    @Autowired
    Order order;

    @Then("^the customer's order is \'(.+)\'$")
    public void theCustomerSOrderIsAccepted(String expectedState) {

        ResponseEntity<GetCustomerOrderResponse> customerOrderResponseEntity = customerService.GetCustomerOrder("1", "1");
    }

}
