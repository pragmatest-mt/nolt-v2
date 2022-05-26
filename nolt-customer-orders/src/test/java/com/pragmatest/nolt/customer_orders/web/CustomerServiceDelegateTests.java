package com.pragmatest.nolt.customer_orders.web;

import com.pragmatest.nolt.customer_orders.CustomerOrdersApplication;
import com.pragmatest.nolt.customer_orders.services.CustomerOrdersService;
import com.pragmatest.nolt.customer_orders.web.controllers.CustomerServiceDelegate;
import com.pragmatest.nolt.customer_orders.web.models.GetOrderResponse;
import com.pragmatest.nolt.customer_orders.web.models.OrderItem;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderRequest;
import com.pragmatest.nolt.customer_orders.web.models.SubmitOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static com.pragmatest.nolt.customer_orders.helpers.Assertions.*;
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
        when(customerOrdersServiceMock.submitOrder()).thenReturn(expectedOrderId);

        // Act

        ResponseEntity<SubmitOrderResponse> actualResponse = customerServiceDelegate.customerServiceOrdersPost(customerId, request);

        // Assert

        assertNotNull(actualResponse, "Response is null.");

        String id = actualResponse.getBody().getOrderId();
        assertNotNull(id, "Id in response is null.");

        assertEquals(expectedOrderId, id);
        verify(customerOrdersServiceMock, times(1)).submitOrder();
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
