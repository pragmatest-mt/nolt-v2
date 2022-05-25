package com.pragmatest.nolt.customer_orders.web.controllers;

import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceDelegate implements CustomerServiceApiDelegate {

    @Override
    public ResponseEntity<SubmitOrderResponse> customerServiceOrdersPost(String xCustomerId, SubmitOrderRequest submitOrderRequest) {
        String orderId = UUID.randomUUID().toString();
        return ResponseEntity.ok(new SubmitOrderResponse().orderId(orderId));
    }

}
