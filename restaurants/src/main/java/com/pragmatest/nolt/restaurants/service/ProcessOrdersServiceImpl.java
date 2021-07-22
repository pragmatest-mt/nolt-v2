package com.pragmatest.nolt.restaurants.service;

import com.pragmatest.nolt.restaurants.data.entities.OrderEntity;
import com.pragmatest.nolt.restaurants.data.repositories.OrdersRepository;
import com.pragmatest.nolt.restaurants.enums.OrderState;
import com.pragmatest.nolt.restaurants.messaging.events.OrderAcceptedEvent;
import com.pragmatest.nolt.restaurants.messaging.producers.OrderAcceptedProducer;
import com.pragmatest.nolt.restaurants.service.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProcessOrdersServiceImpl implements ProcessOrdersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderAcceptedProducer orderAcceptedProducer;

    @Override
    public Order acceptOrder(String orderId) {

        OrderEntity orderEntity = ordersRepository.findByOrderId(orderId);

        if (OrderState.ACCEPTED.equals(orderEntity.getState())) {
            //TODO handle this
        }

        orderEntity.setState(OrderState.ACCEPTED);
        orderEntity.setEstimatedReadyTime(new Date(System.currentTimeMillis()));

        ordersRepository.save(orderEntity);

        OrderAcceptedEvent orderAcceptedEvent = modelMapper.map(orderEntity, OrderAcceptedEvent.class);
        orderAcceptedProducer.send(orderAcceptedEvent);

        Order order = modelMapper.map(orderEntity, Order.class);

        return order;
    }

    @Override
    public void submitOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        ordersRepository.save(orderEntity);
    }
}
