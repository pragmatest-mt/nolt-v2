package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.OrderEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.services.models.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrdersService {

    @Autowired
    CustomerOrdersRepository repository;

    @Autowired
    ModelMapper mapper;

    public String submitOrder(Order order) {
        OrderEntity orderEntity = mapper.map(order, OrderEntity.class);
        orderEntity.setId(UUID.randomUUID().toString());
        orderEntity = repository.save(orderEntity);
        return orderEntity.getId();
    }

    // TODO - 3. Update the getOrder() to use the repository layer to find an order with matching orderId and customerId,
    //  instead of returning hard-coded values.
    //  This implementation should be driven by the already implemented tests in TODOs 1. and 2.
    //  This implementation is deemed completed once these tests pass successfully.
    public Order getOrder(String orderId, String customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setMenuItemId("burger");
        orderItem.setNotes("extra lettuce");

        List<OrderItem> orderItems = List.of(orderItem);

        order.setOrderItems(orderItems);
        order.setId(orderId);
        return order;
    }
}
