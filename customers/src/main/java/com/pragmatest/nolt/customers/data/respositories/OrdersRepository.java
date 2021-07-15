package com.pragmatest.nolt.customers.data.respositories;

import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
    
}