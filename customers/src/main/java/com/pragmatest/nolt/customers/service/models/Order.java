package com.pragmatest.nolt.customers.service.models;

import java.util.List;

public class Order {

    String userId;
    String id;
    List<OrderItem> orderItems;

    public Order(String userId, String id, List<OrderItem> orderItems) {
        this.userId = userId;
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order(String userId, List<OrderItem> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
