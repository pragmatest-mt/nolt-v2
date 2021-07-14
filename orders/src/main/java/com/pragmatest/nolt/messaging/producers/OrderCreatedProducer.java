package com.pragmatest.nolt.messaging.producers;

import com.pragmatest.nolt.messaging.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OrderCreatedProducer {

    @Value(value = "${order.created.topic}")
    private String orderCreatedTopicName;

    @Autowired
    KafkaTemplate<String, OrderCreatedEvent> orderCreatedKafkaTemplate;

    public void send(OrderCreatedEvent event) {
        orderCreatedKafkaTemplate
                .send(orderCreatedTopicName, event)
                .addCallback(new ListenableFutureCallback<SendResult<String, OrderCreatedEvent>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Oops failed to produce");
                    }

                    @Override
                    public void onSuccess(SendResult<String, OrderCreatedEvent> stringOrderCreatedEventSendResult) {
                        System.out.println("Yeyyy msg out.");
                    }
                });
    }

}
