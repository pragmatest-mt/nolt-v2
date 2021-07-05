package com.pragmatest.nolt.messaging.events;

public class OrderCreatedEvent {

    String orderId;
    String orderName;
    String menuItemId;

    public OrderCreatedEvent() {

    }

    public OrderCreatedEvent(String orderId, String orderName, String menuItemId) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.menuItemId = menuItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", menuItemId='" + menuItemId + '\'' +
                '}';
    }
}
