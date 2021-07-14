package com.pragmatest.nolt.messaging.commands;

public class AddToOrderCommand {

    String userId;
    String menuItemId;
    int quantity;

    public AddToOrderCommand() {

    }

    public AddToOrderCommand(String userId, String menuItemId, int quantity) {
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public AddToOrderCommand(String menuItemId, int quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "AddToOrderCommand{" +
                "menuItemId='" + menuItemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
