package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.models.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.e2e.tests.common.services.customers.CustomerOrderService;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CustomerOrderStateCheckSteps {

    @Autowired
    CustomerOrderService customerService;

    @Then("^the customer's order is \'(.+)\'$")
    public void theCustomerSOrderIsAccepted(String expectedState) {

        ResponseEntity<GetCustomerOrderResponse> customerOrderResponseEntity = customerService.GetCustomerOrder("1", "1");
    }

}
