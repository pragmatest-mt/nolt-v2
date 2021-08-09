package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.services.restaurants.ResturantsOrderService;
import com.pragmatest.nolt.e2e.tests.common.state.Order;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class ResturantOrderAcceptanceSteps {

    @Autowired
    ResturantsOrderService resturantOrderService;

    @Autowired
    Order order;

    @When("the restaurant accepts the order")
    public void theRestaurantAcceptsTheOrder() {

    }
}
