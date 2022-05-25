package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.web.controllers.CustomerServiceDelegate;
import com.pragmatest.nolt.customer_orders.web.models.OrderItem;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static com.pragmatest.nolt.customer_orders.helpers.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceDelegateTests {

    @Autowired
    CustomerServiceDelegate customerServiceDelegate;

    @Test
    public void testSubmitOrderValidOrder() {
        // Arrange

        String customerId = UUID.randomUUID().toString();
        SubmitOrderRequest request = new SubmitOrderRequest()
                .orderItems(
                        List.of(new OrderItem().menuItemId("burger").quantity(1).notes("no lettuce"))
                );

        // Act

        ResponseEntity<SubmitOrderResponse> actualResponse = customerServiceDelegate.customerServiceOrdersPost(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getBody().getOrderId();
        assertNotNull(id, "Id in response is null.");

        assertIsValidUuid(id);
    }

    // TODO 2. Start writing test verifying that when the get order method is called from the delegate, a valid order is retrieved.
    @Test
    public void testGetOrderValidId() {

        // Arrange

        // TODO 2.a. Generate fake customerId and orderId.

        // Act

        // TODO 2.b. Call the get order method from the delegate and save the response in a variable.

        // Assert

        // TODO 2.c. Assert that a valid order is returned.
    }
}
