package com.pragmatest.nolt.contracts.restaurants;

import com.pragmatest.nolt.contracts.OrderItem;

import java.util.List;

public class SubmitCustomerOrderRequest {

    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
