package com.pragmatest.nolt.e2e.tests.common.models.restaurants;

import com.pragmatest.nolt.e2e.tests.common.models.OrderItem;

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
