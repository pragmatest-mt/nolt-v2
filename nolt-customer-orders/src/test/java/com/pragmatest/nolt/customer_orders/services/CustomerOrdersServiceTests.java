package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.OrderEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static com.pragmatest.nolt.customer_orders.helpers.Assertions.assertIsValidUuid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerOrdersServiceTests {

    @Autowired
    CustomerOrdersService customerOrdersService;

    @MockBean
    CustomerOrdersRepository customerOrdersRepository;

    @Test
    public void testSubmitOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        Order order = new Order();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);

        when(customerOrdersRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        // Act
        String id = customerOrdersService.submitOrder(order);

        // Assert
        assertNotNull(id, "Id in response is null.");
        assertIsValidUuid(id);
        assertEquals(orderEntity.getId(),id);
        verify(customerOrdersRepository, times(1)).save(any(OrderEntity.class));
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