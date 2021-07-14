package com.pragmatest.nolt.service;

import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.service.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface OrdersService {
    Order TryCreateOrder(String userId);

    OrderItem TryAddMenuItem(String userId, String menuItemId, int quantity);
}
