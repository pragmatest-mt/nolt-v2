package com.pragmatest.nolt.restaurants.data.entities;

import com.pragmatest.nolt.restaurants.enums.OrderState;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderEntity {

    @Id
    private String orderId;

    private String userId;

    private OrderState state;

    private Date estimatedReadyTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
    private List<OrderItemEntity> orderItems; // check on this

    public OrderEntity(String userId) {
        this();
        this.userId = userId;
    }

    public Date getEstimatedReadyTime() {
        return estimatedReadyTime;
    }

    public void setEstimatedReadyTime(Date estimatedReadyTime) {
        this.estimatedReadyTime = estimatedReadyTime;
    }

    public OrderEntity() {
        this.orderId = UUID.randomUUID().toString();
        this.state = OrderState.SUBMITTED;
        this.estimatedReadyTime = new Date(System.currentTimeMillis());
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}

