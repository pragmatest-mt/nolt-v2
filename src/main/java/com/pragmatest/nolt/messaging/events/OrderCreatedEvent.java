package com.pragmatest.nolt.messaging.events;

public class OrderCreatedEvent {

    String userId;
    String orderId;

    public OrderCreatedEvent(String userId, String orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
