package com.pragmatest.nolt.e2e.tests.common.services.customers;

import com.pragmatest.nolt.contracts.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.contracts.restaurants.SubmitCustomerOrderRequest;
import com.pragmatest.nolt.contracts.restaurants.SubmitCustomerOrderResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(value = "stubs.customers.enabled", havingValue = "true", matchIfMissing = false)
public class CustomerOrderServiceStub implements CustomerOrderService {

    @Override
    public ResponseEntity<GetCustomerOrderResponse> GetCustomerOrder(String customerId, String orderId) {
        return new ResponseEntity<GetCustomerOrderResponse>(new GetCustomerOrderResponse(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SubmitCustomerOrderResponse> SubmitCustomerOrder(String customerId, SubmitCustomerOrderRequest submitCustomerOrderRequest) {
        SubmitCustomerOrderResponse submitCustomerOrderResponse = new SubmitCustomerOrderResponse();
        submitCustomerOrderResponse.setOrderId(UUID.randomUUID().toString());

        return new ResponseEntity<SubmitCustomerOrderResponse>(submitCustomerOrderResponse, HttpStatus.ACCEPTED);
    }
}
