package com.pragmatest.nolt.customers.messaging.handlers.utils;

import com.pragmatest.nolt.customers.messaging.events.OrderAcceptedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

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
