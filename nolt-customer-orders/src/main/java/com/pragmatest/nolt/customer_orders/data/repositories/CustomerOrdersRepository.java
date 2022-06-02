package com.pragmatest.nolt.customer_orders.data.repositories;

import com.pragmatest.nolt.customer_orders.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrdersRepository extends JpaRepository<OrderEntity, String> {
}
