package com.pragmatest.nolt.e2e.tests.common.models.restaurants;

import com.pragmatest.nolt.e2e.tests.common.models.OrderItem;

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
