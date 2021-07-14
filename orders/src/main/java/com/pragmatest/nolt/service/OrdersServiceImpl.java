package com.pragmatest.nolt.service;

import com.pragmatest.nolt.data.entities.OrderEntity;
import com.pragmatest.nolt.data.entities.OrderItemEntity;
import com.pragmatest.nolt.data.respositories.OrdersRepository;
import com.pragmatest.nolt.service.models.Order;
import com.pragmatest.nolt.service.models.OrderItem;
import com.pragmatest.nolt.web.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements  OrdersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Order TryCreateOrder(String userId) {
        List<OrderEntity> orders = ordersRepository.findByUserId(userId);

        // If order for user doesn't exist, create it.
        if (orders.isEmpty()) {
            OrderEntity orderEntity = ordersRepository.save(new OrderEntity(userId));
            Order order = modelMapper.map(orderEntity, Order.class);
            return order;
        }

        return null;
    }

    @Override
    public OrderItem TryAddMenuItem(String userId, String menuItemId, int quantity) {
        List<OrderEntity> orders = ordersRepository.findByUserId(userId);

        // If there are no orders, return null
        if (orders.isEmpty()) {
            return null;
        }

        // Otherwise, get the last one
        OrderEntity order = orders.get(orders.size() - 1);

        // Retrieve items with the same id, if any.
        Optional<OrderItemEntity> orderItemOptional =
                order.getOrderItems()
                        .stream()
                        .filter(orderItem -> orderItem.getMenuItemId().equalsIgnoreCase(menuItemId))
                        .findFirst();

        // Update the order with the new order item
        if (orderItemOptional.isEmpty()) {
            order.getOrderItems().add(new OrderItemEntity(menuItemId, quantity));
        } else {
            OrderItemEntity existingOrderItem = orderItemOptional.get();
            existingOrderItem.setQuantity(existingOrderItem.getQuantity() + quantity);
            // TODO check with debugger if list in order is updated with the new quantity through reference magic
        }

        // Save it
        ordersRepository.save(order);

        return new OrderItem(order.getId(), menuItemId, quantity);
    }

    @Override
    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = ordersRepository.findAll();

        Type returnType = new TypeToken<List<OrderResponse>>() {
        }.getType();

        List<Order> orders = modelMapper.map(orderEntities, returnType);

        return orders;
    }

}