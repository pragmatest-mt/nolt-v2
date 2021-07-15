package com.pragmatest.nolt.restaurants.messaging.producers;

import com.pragmatest.nolt.restaurants.messaging.events.OrderAcceptedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OrderAcceptedProducer {

    @Value(value = "${order.accepted.topic}")
    private String orderAcceptedTopicName;

    @Autowired
    KafkaTemplate<String, OrderAcceptedEvent> orderSubmittedKafkaTemplate;

    public void send(OrderAcceptedEvent orderAcceptedEvent) {
        orderSubmittedKafkaTemplate
                .send(orderAcceptedTopicName, orderAcceptedEvent)
                .addCallback(new ListenableFutureCallback<SendResult<String, OrderAcceptedEvent>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Failed to produce Order Accepted Event with ID: " + orderAcceptedEvent.getOrderId());
                    }

                    @Override
                    public void onSuccess(SendResult<String, OrderAcceptedEvent> stringOrderSubmittedEventSendResult) {
                        System.out.println("Produced Order Accepted Event with ID: " + orderAcceptedEvent.getOrderId());
                    }
                });
    }
}
