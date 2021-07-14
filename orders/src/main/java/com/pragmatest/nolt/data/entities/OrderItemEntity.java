package com.pragmatest.nolt.data.entities;

import com.pragmatest.nolt.service.models.OrderItem;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class OrderItemEntity {

    private String menuItemId;
    private int quantity;

    public OrderItemEntity(String menuItemId, int quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public OrderItemEntity() {
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
