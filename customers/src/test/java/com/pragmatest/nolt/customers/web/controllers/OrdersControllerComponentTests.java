package com.pragmatest.nolt.customers.web.controllers;

import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import com.pragmatest.nolt.customers.messaging.events.OrderSubmittedEvent;
import com.pragmatest.nolt.customers.utils.AsyncAssertHelper;
import com.pragmatest.nolt.customers.web.controllers.utils.OrderSubmittedHandler;
import com.pragmatest.nolt.customers.web.requests.OrderItem;
import com.pragmatest.nolt.customers.web.requests.OrderRequest;
import com.pragmatest.nolt.customers.web.responses.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "kafka.enabled=true")
@AutoConfigureTestEntityManager
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class OrdersControllerComponentTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OrderSubmittedHandler orderSubmittedHandler;

    @Autowired
    private AsyncAssertHelper helper;

    @Test
    public void whenOrderIsSubmitted_thenOrderIsSavedAndMessageIsPublished() {

        //// Arrange

        OrderRequest orderRequest = new OrderRequest("Andrea", List.of(new OrderItem("Burger", 1, "Extra Coleslaw")));
        String endpoint = "/submit";

        //// Act

        ResponseEntity<OrderResponse> response = testRestTemplate.postForEntity(endpoint, orderRequest, OrderResponse.class);

        //// Assert

        OrderEntity savedEntity = transactionTemplate.execute((conn) -> testEntityManager.find(OrderEntity.class, response.getBody().getOrderId()));
        assertThat(savedEntity.getUserId()).isEqualTo("Andrea");

        helper.asyncAssert(() -> {
            return orderSubmittedHandler.getEventReceived() != null;
        });

        OrderSubmittedEvent eventReceived = orderSubmittedHandler.getEventReceived();
        assertThat(eventReceived.getUserId()).isEqualTo("Andrea");
    }
}