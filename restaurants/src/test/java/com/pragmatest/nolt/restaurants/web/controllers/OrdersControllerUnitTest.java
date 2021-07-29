package com.pragmatest.nolt.restaurants.web.controllers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurants.enums.OrderState;
import com.pragmatest.nolt.restaurants.helpers.HelperMethods;
import com.pragmatest.nolt.restaurants.service.ProcessOrdersServiceImpl;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.service.models.OrderItem;
import com.pragmatest.nolt.restaurants.web.responses.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class OrdersControllerUnitTest {

    @Autowired
    private OrdersController ordersController;

    @MockBean
    private ProcessOrdersServiceImpl processOrdersMockService;

    @Test
    public void testAcceptOrderValidOrder() throws ParseException {
        //Arrange
        String orderId = "f615f211-2d00-4e58-aa60-3d24013e0542";
        Date estimatedReadyTime = HelperMethods.generateEstimatedDeliveryTime(1);

        List<com.pragmatest.nolt.restaurants.web.responses.OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new com.pragmatest.nolt.restaurants.web.responses.OrderItem(orderId, "pizza margherita", 2));
        orderItems.add(new com.pragmatest.nolt.restaurants.web.responses.OrderItem(orderId, "burger", 1, "extra coleslaw"));

        OrderResponse expectedOrderResponse = new OrderResponse(orderId, orderItems, OrderState.ACCEPTED, estimatedReadyTime);

        List<OrderItem> serviceOrderItems = new ArrayList<>();
        serviceOrderItems.add(new OrderItem(orderId, "pizza margherita", 2));
        serviceOrderItems.add(new OrderItem(orderId, "burger", 1, "extra coleslaw"));

        Order orderOutput = new Order(orderId, serviceOrderItems, OrderState.ACCEPTED, estimatedReadyTime);

        when(processOrdersMockService.acceptOrder(orderId, estimatedReadyTime)).thenReturn(orderOutput);

        //Act
        OrderResponse actualOrderResponse = ordersController.accept(orderId, estimatedReadyTime);

        //Assert
        assertTrue(DeepEquals.deepEquals(actualOrderResponse, expectedOrderResponse));
        verify(processOrdersMockService, times(1)).acceptOrder(orderId, estimatedReadyTime);
    }
}
