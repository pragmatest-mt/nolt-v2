package com.pragmatest.nolt.restaurants.service.models;

import com.pragmatest.nolt.restaurants.enums.OrderState;

import java.util.Date;
import java.util.List;

public class Order {

    String orderId;
    List<OrderItem> orderItems;
    OrderState orderState;
    Date estimatedReadyTime;

    public Order(String orderId, List<OrderItem> orderItems, OrderState orderState, Date estimatedReadyTime) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.estimatedReadyTime = estimatedReadyTime;
    }

    public Order() {
    }

    public Date getEstimatedReadyTime() {
        return estimatedReadyTime;
    }

    public void setEstimatedReadyTime(Date estimatedReadyTime) {
        this.estimatedReadyTime = estimatedReadyTime;
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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
