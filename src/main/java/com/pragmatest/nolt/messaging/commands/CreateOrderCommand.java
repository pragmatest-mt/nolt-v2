package com.pragmatest.nolt.messaging.commands;

public class CreateOrderCommand {

    String orderName;
    String menuItemId;

    public CreateOrderCommand() {

    }

    public CreateOrderCommand(String orderName, String menuItemId) {
        this.orderName = orderName;
        this.menuItemId = menuItemId;
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
        return "CreateOrderCommand{" +
                "orderName='" + orderName + '\'' +
                ", menuItemId='" + menuItemId + '\'' +
                '}';
    }
}
