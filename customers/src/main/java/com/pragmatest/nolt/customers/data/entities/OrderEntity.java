package com.pragmatest.nolt.customers.data.entities;

import com.pragmatest.nolt.customers.enums.OrderState;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderEntity {

    @Id
    private String id;

    private String userId;

    private OrderState state;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
    private List<OrderItemEntity> orderItems; // check on this

    public OrderEntity(String userId) {
        this();
        this.userId = userId;
    }

    public OrderEntity() {
        this.id = UUID.randomUUID().toString();
        this.state = OrderState.SUBMITTED;
    }

    public String getId() {
        return id;
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
