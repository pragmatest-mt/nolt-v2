package com.pragmatest.nolt.e2e.tests.common.services.customers;

import com.pragmatest.nolt.contracts.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.contracts.restaurants.SubmitCustomerOrderRequest;
import com.pragmatest.nolt.contracts.restaurants.SubmitCustomerOrderResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerOrderService {

    ResponseEntity<GetCustomerOrderResponse> GetCustomerOrder(String customerId, String orderId);
    ResponseEntity<SubmitCustomerOrderResponse> SubmitCustomerOrder(String customerId, SubmitCustomerOrderRequest submitCustomerOrderRequest);

}
