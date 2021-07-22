package com.pragmatest.nolt.restaurants.messaging.events;

import com.pragmatest.nolt.restaurants.service.models.OrderItem;

import java.util.List;

public class OrderSubmittedEvent {

    private String orderId;
    private List<OrderItem> orderItems;

    public OrderSubmittedEvent(String orderId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderItems = orderItems;
    }

    public OrderSubmittedEvent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
