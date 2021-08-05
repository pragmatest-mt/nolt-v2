package com.pragmatest.nolt.e2e.tests.common.services.restaurants;

import com.pragmatest.nolt.contracts.customers.AcceptOrderRequest;
import com.pragmatest.nolt.contracts.customers.AcceptOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResturantOrderServiceStub implements ResturantsOrderService {
    @Override
    public ResponseEntity<AcceptOrderResponse> acceptOrder(String restaurantId, String orderId, AcceptOrderRequest acceptOrderRequest) {
        AcceptOrderResponse response = new AcceptOrderResponse();
        return new ResponseEntity<AcceptOrderResponse>(response, HttpStatus.OK);
    }
}
