package com.pragmatest.nolt.contracts.restaurants;

import com.pragmatest.nolt.contracts.OrderItem;

import java.util.List;

public class SubmitCustomerOrderResponse {

    private String orderId;
    private List<OrderItem> orderItems;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
