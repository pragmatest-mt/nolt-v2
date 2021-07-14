package com.pragmatest.nolt.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${order.add-to-order.topic}")
    private String addToOrderTopicName;

    @Value(value = "${order.created.topic}")
    private String orderCreatedTopicName;

    @Value(value = "${order.menu-item-added.topic}")
    private String menuItemAddedTopicName;

    @Value(value = "${order.created.topic}")
    private String orderSubmittedTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic addToOrderTopic() {
        return new NewTopic(addToOrderTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderCreatedTopic() {
        return new NewTopic(orderCreatedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic menuItemAddedTopic() {
        return new NewTopic(menuItemAddedTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic orderSubmittedTopic() {
        return new NewTopic(orderSubmittedTopicName, 1, (short) 1);
    }
}