package com.pragmatest.nolt.customer_orders.web.controllers;

import com.pragmatest.nolt.customer_orders.web.models.GetOrderResponse;
import com.pragmatest.nolt.customer_orders.web.models.OrderItem;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceDelegate implements CustomerServiceApiDelegate {

    @Override
    public ResponseEntity<SubmitOrderResponse> customerServiceOrdersPost(String xCustomerId, SubmitOrderRequest submitOrderRequest) {
        String orderId = UUID.randomUUID().toString();
        return ResponseEntity.ok(new SubmitOrderResponse().orderId(orderId));
    }

    @Override
    public ResponseEntity<GetOrderResponse> getCustomerOrder(String orderId, String xCustomerId) {
        List<OrderItem> orderItems = List.of(
                new OrderItem()
                        .quantity(1)
                        .menuItemId("burger")
                        .notes("extra lettuce")
        );

        GetOrderResponse getOrderResponse = new GetOrderResponse()
                .customerId(xCustomerId)
                .id(orderId)
                .orderItems(orderItems);

        return ResponseEntity.ok(getOrderResponse);
    }
}
