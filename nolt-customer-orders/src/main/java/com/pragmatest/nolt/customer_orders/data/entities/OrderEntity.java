package com.pragmatest.nolt.customer_orders.data.entities;

import com.pragmatest.nolt.customer_orders.data.enums.OrderState;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderEntity {

    @ElementCollection
    @CollectionTable(name="ORDER_ITEMS", joinColumns=@JoinColumn(name="ORDER_ID"))
    private List<OrderItemEntity> orderItems;

    private String customerId;

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

}
