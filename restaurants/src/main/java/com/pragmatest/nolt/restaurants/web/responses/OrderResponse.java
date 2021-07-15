package com.pragmatest.nolt.restaurants.web.responses;

import com.pragmatest.nolt.restaurants.enums.OrderState;
import com.pragmatest.nolt.restaurants.service.models.OrderItem;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    String id;
    List<OrderItem> orderItems;
    OrderState orderState;
    Date estimatedReadyTime;

    public OrderResponse(String id, List<OrderItem> orderItems, OrderState orderState, Date estimatedReadyTime) {
        this.id = id;
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.estimatedReadyTime = estimatedReadyTime;
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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Date getEstimatedReadyTime() {
        return estimatedReadyTime;
    }

    public void setEstimatedReadyTime(Date estimatedReadyTime) {
        this.estimatedReadyTime = estimatedReadyTime;
    }
}
