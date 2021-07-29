package com.pragmatest.nolt.customers.web.controllers;

import com.cedarsoftware.util.DeepEquals;
import com.pragmatest.nolt.customers.service.OrdersService;
import com.pragmatest.nolt.customers.service.models.OrderItem;
import com.pragmatest.nolt.customers.service.models.Order;
import com.pragmatest.nolt.customers.web.matchers.OrderMatcher;
import com.pragmatest.nolt.customers.web.requests.OrderRequest;
import com.pragmatest.nolt.customers.web.responses.OrderResponse;
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
        String userId = "a6bb292e-f08b-11eb-9a03-0242ac130003";
        String orderId = "f615f211-2d00-4e58-aa60-3d24013e0542";

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem("pizza margherita", 2));
        orderItems.add(new OrderItem("burger", 1, "extra coleslaw"));

        Order orderInput = new Order(userId, orderItems);

        Order orderOutput = new Order(userId, orderItems);
        orderOutput.setOrderId(orderId);

        when(ordersMockService.submitOrder(argThat(new OrderMatcher(orderInput)))).thenReturn(orderOutput);

        List<com.pragmatest.nolt.customers.web.requests.OrderItem> requestOrderItems = new ArrayList<>();
        requestOrderItems.add(
                new com.pragmatest.nolt.customers.web.requests.OrderItem("pizza margherita", 2));
        requestOrderItems.add(
                new com.pragmatest.nolt.customers.web.requests.OrderItem("burger", 1, "extra coleslaw"));

        OrderRequest orderRequest = new OrderRequest(userId, requestOrderItems);
        OrderResponse expectedOrderResponse = new OrderResponse(orderId, userId, requestOrderItems);

        //Act
        OrderResponse actualOrderResponse = ordersController.submit(orderRequest);

        //Assert
        assertTrue(DeepEquals.deepEquals(actualOrderResponse, expectedOrderResponse));
        verify(ordersMockService, times(1)).submitOrder(argThat(new OrderMatcher(orderInput)));
    }
}
