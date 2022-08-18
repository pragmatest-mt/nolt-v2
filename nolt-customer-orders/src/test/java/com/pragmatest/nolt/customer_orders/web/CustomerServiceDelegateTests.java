package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.CustomerOrdersApplication;
import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import com.pragmatest.nolt.customer_orders.web.controllers.CustomerServiceDelegate;
import com.pragmatest.nolt.customer_orders.web.models.GetOrderResponse;
import com.pragmatest.nolt.customer_orders.web.models.OrderItem;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CustomerOrdersApplication.class)
@ActiveProfiles("test")
public class CustomerServiceDelegateTests {

    @Autowired
    CustomerServiceDelegate customerServiceDelegate;

    @MockBean
    CustomerOrdersService customerOrdersServiceMock;

    @Test
    public void testSubmitOrderValidOrder() {
        // Arrange

        String customerId = UUID.randomUUID().toString();
        SubmitOrderRequest request = new SubmitOrderRequest()
                .orderItems(
                        List.of(new OrderItem().menuItemId("burger").quantity(1).notes("no lettuce"))
                );

        String expectedOrderId = UUID.randomUUID().toString();

        when(customerOrdersServiceMock.submitOrder(any(Order.class))).thenReturn(expectedOrderId);

        // Act

        ResponseEntity<SubmitOrderResponse> actualResponse = customerServiceDelegate.customerServiceOrdersPost(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getBody().getOrderId();
        assertNotNull(id, "Id in response is null.");

        assertEquals(expectedOrderId, id);
        verify(customerOrdersServiceMock, times(1)).submitOrder(any(Order.class));
    }

    @Test
    public void testGetOrderValidId() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        Order order = new Order();
        order.setId(orderId);
        order.setCustomerId(customerId);
        order.setOrderItems(List.of(new com.pragmatest.nolt.customer_orders.services.models.OrderItem()));

        when(customerOrdersServiceMock.getOrder(orderId, customerId)).thenReturn(order);

        // Act
        ResponseEntity<GetOrderResponse> actualResponse = customerServiceDelegate.getCustomerOrder(orderId, customerId);

        // Assert
        assertNotNull(actualResponse, "Response is null.");
        assertThat(order).usingRecursiveComparison().isEqualTo(actualResponse.getBody());

        verify(customerOrdersServiceMock, times(1)).getOrder(orderId, customerId);
    }

    // TODO - 4. Look at the following test, which verifies that HTTP Status Code 404 is returned when the null is returned
    //  by the service layer.
    @Test
    public void testGetNonExistentOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        Order order = new Order();
        order.setId(orderId);
        order.setCustomerId(customerId);
        order.setOrderItems(List.of(new com.pragmatest.nolt.customer_orders.services.models.OrderItem()));

        when(customerOrdersServiceMock.getOrder(orderId, customerId)).thenReturn(null);

        // Act
        ResponseEntity<GetOrderResponse> actualResponse = customerServiceDelegate.getCustomerOrder(orderId, customerId);

        // Assert
        assertNotNull(actualResponse, "Response is null.");
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        verify(customerOrdersServiceMock, times(1)).getOrder(orderId, customerId);
    }
}