package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.pragmatest.nolt.customer_orders.helpers.Assertions.assertIsValidUuid;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

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
}
