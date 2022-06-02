package com.pragmatest.nolt.customer_orders.web.controllers;

import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.web.models.GetOrderResponse;
import com.pragmatest.nolt.customer_orders.web.models.OrderItem;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceDelegate implements CustomerServiceApiDelegate {

    @Autowired
    CustomerOrdersService customerOrdersService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<SubmitOrderResponse> customerServiceOrdersPost(String xCustomerId, SubmitOrderRequest submitOrderRequest) {
        Order orderSubmission = modelMapper.map(submitOrderRequest, Order.class);
        orderSubmission.setCustomerId(xCustomerId);
        String orderId = customerOrdersService.submitOrder(orderSubmission);
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
