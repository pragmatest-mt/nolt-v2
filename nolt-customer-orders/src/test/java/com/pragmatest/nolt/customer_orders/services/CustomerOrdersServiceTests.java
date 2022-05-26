package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static com.pragmatest.nolt.customer_orders.helpers.Assertions.assertIsValidUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class CustomerOrdersServiceTests {

    @Autowired
    CustomerOrdersService customerOrdersService;

    @Test
    public void testSubmitOrder() {
        // Arrange
        Order order = new Order();

        // Act
        String id = customerOrdersService.submitOrder(order);

        // Assert
        assertNotNull(id, "Id in response is null.");
        assertIsValidUuid(id);
    }

    @Test
    public void testGetOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        // Act
        Order order = customerOrdersService.getOrder(orderId, customerId);

        // Assert
        assertNotNull(order);
        assertEquals(orderId, order.getId());
        assertEquals(customerId, order.getCustomerId());
    }
}