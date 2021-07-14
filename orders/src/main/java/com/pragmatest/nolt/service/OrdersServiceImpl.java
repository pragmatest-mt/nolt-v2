package com.pragmatest.nolt.service;

import com.pragmatest.nolt.data.entities.OrderEntity;
import com.pragmatest.nolt.data.entities.OrderItemEntity;
import com.pragmatest.nolt.data.respositories.OrdersRepository;
import com.pragmatest.nolt.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.messaging.producers.OrderSubmittedProducer;
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

    @Autowired
    private OrderSubmittedProducer orderSubmittedProducer;

    @Override
    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = ordersRepository.findAll();

        Type returnType = new TypeToken<List<OrderResponse>>() {
        }.getType();

        List<Order> orders = modelMapper.map(orderEntities, returnType);

        return orders;
    }

    @Override
    public Order submitOrder(Order order) {

        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        orderEntity = ordersRepository.save(orderEntity);

        OrderSubmittedEvent orderSubmittedEvent = modelMapper.map(orderEntity, OrderSubmittedEvent.class);
        orderSubmittedProducer.send(orderSubmittedEvent);

        order = modelMapper.map(orderEntity, Order.class);

        return order;
    }

}