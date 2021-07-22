package com.pragmatest.nolt.customers.messaging.handlers;

import com.pragmatest.nolt.customers.data.entities.OrderEntity;
import com.pragmatest.nolt.customers.data.entities.OrderItemEntity;
import com.pragmatest.nolt.customers.enums.OrderState;
import com.pragmatest.nolt.customers.messaging.events.OrderAcceptedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.fail;

@Configuration
class OrderAcceptedHandlerTestConfiguration {

    @Value(value = "localhost:9092")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, OrderAcceptedEvent> orderAcceptedProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, OrderAcceptedEvent> orderAcceptedKafkaTemplate() {
        return new KafkaTemplate<String, OrderAcceptedEvent>(orderAcceptedProducerFactory());
    }

}

@SpringBootTest
@AutoConfigureTestEntityManager
@DirtiesContext
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

    @Test
    public void givenOrderIsSubmitted_whenOrderIsAccepted_thenStateIsAccepted() {

        //// Arrange

        // Insert order with ID X into DB with state = SUBMITTED

        transactionTemplate.execute(
                (connection) -> {
                    OrderEntity orderEntity = new OrderEntity("orderId", "andrea", OrderState.SUBMITTED, List.of(new OrderItemEntity("menuItemId", 1, "no lettuce")));
                    return testEntityManager.persist(orderEntity);
                }
        );

        OrderAcceptedEvent orderAcceptedEvent = new OrderAcceptedEvent("orderId", Date.from(Instant.now()));

        //// Act

        // Produce OrderAcceptedEvent with ID X

        orderAcceptedEventKafkaTemplate.send(orderAcceptedTopicName, orderAcceptedEvent);

        //// Assert

        // Read state of order with ID X and assert state = ACCEPTED

        try {
            await().atMost(5, SECONDS).until(() -> {
                OrderEntity orderEntity = transactionTemplate.execute(
                        (connection) -> testEntityManager.find(OrderEntity.class, "orderId")
                );
                return orderEntity.getState().equals(OrderState.ACCEPTED);
            });
        } catch(ConditionTimeoutException e) {
            fail("Order was not accepted within the given consistency period of 5 seconds.");
        }
    }

}



