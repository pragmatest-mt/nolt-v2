package com.pragmatest.nolt.restaurants.messaging.events;

import com.pragmatest.nolt.restaurants.service.models.OrderItem;

import java.util.List;

public class OrderSubmittedEvent {

    private String id;
    private List<OrderItem> orderItems;

    public OrderSubmittedEvent(String id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
