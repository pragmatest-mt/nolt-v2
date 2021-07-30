package com.pragmatest.nolt.end2end.tests.stepdefinitions;

import io.cucumber.java.en.When;

public class ResturantOrderAcceptanceSteps {
    @When("the restaurant accepts the order")
    public void theRestaurantAcceptsTheOrder() {
        System.out.println("Restaurant accepted the order");
    }
}
