package com.pragmatest.nolt.messaging.events;

public class MenuItemAddedEvent {

    String orderId;
    String userId;
    String menuItemId;
    int quantity;

    public MenuItemAddedEvent(String orderId, String userId, String menuItemId, int quantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
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

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
