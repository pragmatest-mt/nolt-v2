package com.pragmatest.nolt.customers.web.responses;

import com.pragmatest.nolt.customers.web.requests.OrderItem;

import java.util.List;

public class OrderResponse {

    String id;
    String userId;
    List<OrderItem> orderItems;

    public OrderResponse(String id, String userId, List<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
    }

    public OrderResponse() {
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
