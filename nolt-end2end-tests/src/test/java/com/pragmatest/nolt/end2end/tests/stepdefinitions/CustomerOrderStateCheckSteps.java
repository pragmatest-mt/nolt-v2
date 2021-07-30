package com.pragmatest.nolt.end2end.tests.stepdefinitions;

import io.cucumber.java.en.Then;

public class CustomerOrderStateCheckSteps {

    @Then("the customer's order is accepted")
    public void theCustomerSOrderIsAccepted() {
        System.out.println("Order is accepted");
    }

}
