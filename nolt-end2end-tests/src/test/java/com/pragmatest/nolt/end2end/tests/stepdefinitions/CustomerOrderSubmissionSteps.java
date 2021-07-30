package com.pragmatest.nolt.end2end.tests.stepdefinitions;

import io.cucumber.java.en.Given;

public class CustomerOrderSubmissionSteps {

    @Given("a customer submits an order to a restaurant")
    public void aCustomerSubmitsAnOrderToARestaurant() {
        System.out.println("order submitted");
    }

}
