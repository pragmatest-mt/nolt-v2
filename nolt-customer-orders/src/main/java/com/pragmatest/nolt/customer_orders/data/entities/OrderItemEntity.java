package com.pragmatest.nolt.customer_orders.data.entities;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemEntity {

    private String menuItemId;
    private int quantity;
    private String notes;

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
