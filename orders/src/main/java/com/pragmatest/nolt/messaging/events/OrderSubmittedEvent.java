package com.pragmatest.nolt.messaging.events;

import java.util.List;

public class OrderSubmittedEvent {

    private String id;
    private String userId;
    private List<OrderItem> orderItems;

    public OrderSubmittedEvent(String id, String userId, List<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public OrderSubmittedEvent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
