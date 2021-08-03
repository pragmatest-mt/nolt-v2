package com.pragmatest.nolt.end2end.tests.common.models;

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
