package com.pragmatest.nolt.customer_orders.services;

import com.pragmatest.nolt.customer_orders.data.entities.OrderEntity;
import com.pragmatest.nolt.customer_orders.data.entities.OrderItemEntity;
import com.pragmatest.nolt.customer_orders.data.repositories.CustomerOrdersRepository;
import com.pragmatest.nolt.customer_orders.services.models.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
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

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setCustomerId(customerId);

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setMenuItemId("burger");
        orderItemEntity.setQuantity(2);
        orderItemEntity.setNotes("extra coleslaw");

        orderEntity.setOrderItems(List.of(orderItemEntity));

        ExampleOrderEntityMatcher exampleOrderEntityMatcher = new ExampleOrderEntityMatcher(Example.of(orderEntity));

        when(customerOrdersRepository.findOne(argThat(exampleOrderEntityMatcher))).thenReturn(Optional.of(orderEntity));

        // Act
        Order order = customerOrdersService.getOrder(orderId, customerId);

        // Assert
        assertThat(order)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(orderEntity);

        verify(customerOrdersRepository, times(1)).findOne(any(Example.class));
    }

    @Test
    public void testGetNonExistentOrder() {
        // Arrange
        String orderId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();

        when(customerOrdersRepository.findOne(any(Example.class))).thenReturn(Optional.empty());

        // Act
        Order order = customerOrdersService.getOrder(orderId, customerId);

        // Assert
        assertThat(order).isNull();

        verify(customerOrdersRepository, times(1)).findOne(any(Example.class));
    }

    class ExampleOrderEntityMatcher implements ArgumentMatcher<Example<OrderEntity>> {

        private OrderEntity leftOrderEntity;

        public ExampleOrderEntityMatcher(Example<OrderEntity> left) {
            this.leftOrderEntity = left.getProbe();
        }

        @Override
        public boolean matches(Example<OrderEntity> right) {
            OrderEntity rightOrderEntity = right != null ? right.getProbe() : null ;

            return leftOrderEntity != null && rightOrderEntity != null &&
                    leftOrderEntity.getCustomerId().equals(rightOrderEntity.getCustomerId()) &&
                    leftOrderEntity.getId().equals(rightOrderEntity.getId()) &&
                    rightOrderEntity.getOrderItems() == null;
        }
    }
}