package com.pragmatest.nolt.restaurants.configuration;

import com.pragmatest.nolt.restaurants.messaging.events.OrderSubmittedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@ConditionalOnProperty(value = "kafka.enabled", matchIfMissing = true)
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    public ConsumerFactory<String, OrderSubmittedEvent> orderSubmittedEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new ErrorHandlingDeserializer(new JsonDeserializer<>(OrderSubmittedEvent.class,
                        false)));
        }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderSubmittedEvent> orderSubmittedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderSubmittedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderSubmittedEventConsumerFactory());
        factory.setErrorHandler(new SeekToCurrentErrorHandler());

        return factory;
    }
}
