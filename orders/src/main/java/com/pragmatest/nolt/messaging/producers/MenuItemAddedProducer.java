package com.pragmatest.nolt.messaging.producers;

import com.pragmatest.nolt.messaging.events.MenuItemAddedEvent;
import com.pragmatest.nolt.messaging.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MenuItemAddedProducer {

    @Value(value = "${order.menu-item-added.topic}")
    private String menuItemAddedTopic;

    @Autowired
    KafkaTemplate<String, MenuItemAddedEvent> menuItemAddedKafkaTemplate;

    public void send(MenuItemAddedEvent event) {
        menuItemAddedKafkaTemplate
                .send(menuItemAddedTopic, event)
                .addCallback(new ListenableFutureCallback<SendResult<String, MenuItemAddedEvent>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Oops failed to produce");
                    }

                    @Override
                    public void onSuccess(SendResult<String, MenuItemAddedEvent> stringOrderCreatedEventSendResult) {
                        System.out.println("Yeyyy msg out.");
                    }
                });
    }
}
