package com.pragmatest.nolt.customers.messaging.handlers;

import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import com.pragmatest.nolt.customers.data.entities.OrderItemEntity;
import com.pragmatest.nolt.customers.enums.OrderState;
import com.pragmatest.nolt.customers.messaging.events.OrderAcceptedEvent;
import com.pragmatest.nolt.customers.utils.AsyncAssertHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@SpringBootTest(properties = {"kafka.enabled=true"})
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class OrderAcceptedHandlerComponentTests {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    public KafkaTemplate<String, OrderAcceptedEvent> orderAcceptedEventKafkaTemplate;

    @Value(value = "${order.accepted.topic}")
    private String orderAcceptedTopicName;

    @Autowired
    private AsyncAssertHelper helper;

    @Test
    public void givenOrderIsSubmitted_whenOrderIsAccepted_thenStateIsAccepted() {

        //// Arrange

        // Insert order with ID X into DB with state = SUBMITTED
        String testOrderId = "397cdec6-e127-4e26-a162-0cba627cfa1a";

        transactionTemplate.execute(
                (connection) -> {
                    OrderEntity orderEntity = new OrderEntity(testOrderId, "andrea", OrderState.SUBMITTED, List.of(new OrderItemEntity("Burger", 1, "no lettuce")));
                    return testEntityManager.persist(orderEntity);
                }
        );

        OrderAcceptedEvent orderAcceptedEvent = new OrderAcceptedEvent(testOrderId, Date.from(Instant.now()));

        //// Act

        // Produce OrderAcceptedEvent with ID X

        orderAcceptedEventKafkaTemplate.send(orderAcceptedTopicName, orderAcceptedEvent);

        //// Assert

        // Read state of order with ID X and assert state = ACCEPTED

        helper.asyncAssert(() -> {
            OrderEntity orderEntity = transactionTemplate.execute(
                    (connection) -> testEntityManager.find(OrderEntity.class, testOrderId)
            );
            return orderEntity.getState().equals(OrderState.ACCEPTED);
        });
    }

}



