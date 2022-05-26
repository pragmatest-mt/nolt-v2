package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.CustomerOrdersApplication;
import com.pragmatest.nolt.customer_orders.web.controllers.CustomerServiceDelegate;
import com.pragmatest.nolt.customer_orders.web.models.GetOrderResponse;
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

@SpringBootTest(classes = CustomerOrdersApplication.class)
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

    @Test
    public void testGetOrderValidId() {

        // Arrange

        String orderId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        // Act

        ResponseEntity<GetOrderResponse> actualResponse = customerServiceDelegate.getCustomerOrder(orderId, customerId);

        // Assert

        assertNotNull(actualResponse, "Response is null.");
        assertEquals(orderId, actualResponse.getBody().getId());
        assertEquals(customerId, actualResponse.getBody().getCustomerId());

    }
}
