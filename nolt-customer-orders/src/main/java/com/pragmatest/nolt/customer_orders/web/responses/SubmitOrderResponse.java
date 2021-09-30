package com.pragmatest.nolt.customer_orders.web.responses;

public class SubmitOrderResponse {

    private String orderId;

    public SubmitOrderResponse(String id) {
        this.orderId = id;
    }

    public SubmitOrderResponse() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}