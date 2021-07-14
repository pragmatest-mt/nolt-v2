package com.pragmatest.nolt.data.respositories;

import com.pragmatest.nolt.data.entities.OrderEntity;
import com.pragmatest.nolt.service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(String userId);
}