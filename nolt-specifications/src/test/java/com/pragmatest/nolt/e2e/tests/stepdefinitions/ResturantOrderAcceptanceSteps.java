package com.pragmatest.nolt.e2e.tests.stepdefinitions;

import com.pragmatest.nolt.e2e.tests.common.models.customers.AcceptOrderRequest;
import com.pragmatest.nolt.e2e.tests.common.services.restaurants.ResturantsOrderService;
import com.pragmatest.nolt.e2e.tests.common.state.Order;
import io.cucumber.java.en.When;
import org.junit.platform.commons.PreconditionViolationException;
import org.springframework.beans.factory.annotation.Autowired;

public class ResturantOrderAcceptanceSteps {

    @Autowired
    ResturantsOrderService resturantOrderService;

    @Autowired
    Order order;

    @When("the restaurant accepts the order")
    public void theRestaurantAcceptsTheOrder() {
        String orderId = order.getOrderId();
        if (orderId == null || orderId.isEmpty()) {
            throw new PreconditionViolationException("Cannot accept order because order id is not set.");
        }

        resturantOrderService.acceptOrder("1", "1", new AcceptOrderRequest());
    }
}
