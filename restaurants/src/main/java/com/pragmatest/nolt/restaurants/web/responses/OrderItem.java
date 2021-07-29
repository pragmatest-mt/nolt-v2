package com.pragmatest.nolt.restaurants.web.responses;

public class OrderItem {
    String orderId;
    String menuItemId;
    int quantity;
    String notes;

    public OrderItem() {
    }

    public OrderItem(String orderId, String menuItemId, int quantity, String notes) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.notes = notes;
    }

    public OrderItem(String orderId, String menuItemId, int quantity) {
        this(orderId, menuItemId, quantity, "");
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
