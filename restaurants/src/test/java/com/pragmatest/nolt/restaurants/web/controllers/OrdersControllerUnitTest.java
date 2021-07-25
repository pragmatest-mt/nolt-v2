package com.pragmatest.nolt.restaurants.web.controllers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurants.enums.OrderState;
import com.pragmatest.nolt.restaurants.helpers.HelperMethods;
import com.pragmatest.nolt.restaurants.service.ProcessOrdersServiceImpl;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.web.responses.OrderItem;
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
    public void testAcceptOrderValidOrder(){
        //Arrange
        String orderId = "1";

        Date deliveryDate = null;
        try {
            deliveryDate = HelperMethods.generateDeliveryDate(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(orderId, "pizza", 1, "note 1"));
        orderItems.add(new OrderItem(orderId, "burger", 2, "note 2"));

        OrderResponse expectedOrderResponse = new OrderResponse(orderId, orderItems, OrderState.ACCEPTED, deliveryDate);

        List<com.pragmatest.nolt.restaurants.service.models.OrderItem> serviceOrderItems = new ArrayList<>();
        serviceOrderItems.add(new com.pragmatest.nolt.restaurants.service.models.OrderItem(orderId, "pizza", 1, "note 1"));
        serviceOrderItems.add(new com.pragmatest.nolt.restaurants.service.models.OrderItem(orderId, "burger", 2, "note 2"));

        Order orderOutput = new Order(orderId, serviceOrderItems, OrderState.ACCEPTED, deliveryDate);

        when(processOrdersMockService.acceptOrder(orderId)).thenReturn(orderOutput);

        //Act
        OrderResponse actualOrderResponse = ordersController.accept(orderId);

        //Assert
        assertTrue(DeepEquals.deepEquals(actualOrderResponse, expectedOrderResponse));
        verify(processOrdersMockService, times(1)).acceptOrder(orderId);
    }
}
