package com.pragmatest.nolt.end2end.tests.common.models;

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
