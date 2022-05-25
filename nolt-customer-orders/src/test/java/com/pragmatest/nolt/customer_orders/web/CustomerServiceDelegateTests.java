package com.pragmatest.nolt.customer_orders.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static com.pragmatest.nolt.customer_orders.helpers.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceDelegateTests {

    @Test
    public void testSubmitOrderValidOrder() {
        // Arrange

        // TODO - Create request.

        // Act

        // TODO - Send request through delegate.

        // Assert

        // TODO - Ensure a valid guid is in response.
    }
}
