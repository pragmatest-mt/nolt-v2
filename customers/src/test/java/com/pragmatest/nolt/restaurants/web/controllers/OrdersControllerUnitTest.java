package com.pragmatest.nolt.restaurants.web.controllers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.restaurants.service.OrdersService;
import com.pragmatest.nolt.restaurants.service.models.OrderItem;
import com.pragmatest.nolt.restaurants.service.models.Order;
import com.pragmatest.nolt.restaurants.web.matchers.OrderMatcher;
import com.pragmatest.nolt.restaurants.web.requests.OrderRequest;
import com.pragmatest.nolt.restaurants.web.responses.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class OrdersControllerUnitTest {

    @Autowired
    private OrdersController ordersController;

    @MockBean
    private OrdersService ordersMockService;

    @Test
    public void testSubmitOrderValidOrder() {
        //Arrange
        String userId = "1";
        String orderId = java.util.UUID.randomUUID().toString();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem("pizza", 1, "note 1"));
        orderItems.add(new OrderItem("burger", 2, "note 2"));

        Order orderInput = new Order(userId, orderItems);

        Order orderOutput = new Order(userId, orderItems);
        orderOutput.setOrderId(orderId);

        when(ordersMockService.submitOrder(argThat(new OrderMatcher(orderInput)))).thenReturn(orderOutput);

        List<com.pragmatest.nolt.restaurants.web.requests.OrderItem> requestOrderItems = new ArrayList<>();
        requestOrderItems.add(
                new com.pragmatest.nolt.restaurants.web.requests.OrderItem("pizza", 1, "note 1"));
        requestOrderItems.add(
                new com.pragmatest.nolt.restaurants.web.requests.OrderItem("burger", 2, "note 2"));

        OrderRequest orderRequest = new OrderRequest(userId, requestOrderItems);
        OrderResponse expectedOrderResponse = new OrderResponse(orderId, userId, requestOrderItems);

        //Act
        OrderResponse actualOrderResponse = ordersController.submit(orderRequest);

        //Assert
        assertTrue(DeepEquals.deepEquals(actualOrderResponse, expectedOrderResponse));
        verify(ordersMockService, times(1)).submitOrder(argThat(new OrderMatcher(orderInput)));
    }
}
