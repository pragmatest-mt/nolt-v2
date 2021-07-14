package com.pragmatest.nolt.messaging.events;

import java.util.Date;

public class OrderAcceptedEvent {

    String id;
    Date estimatedReadyTime;

    public OrderAcceptedEvent(String id, Date estimatedReadyTime) {
        this.id = id;
        this.estimatedReadyTime = estimatedReadyTime;
    }

    public OrderAcceptedEvent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEstimatedReadyTime() {
        return estimatedReadyTime;
    }

    public void setEstimatedReadyTime(Date estimatedReadyTime) {
        this.estimatedReadyTime = estimatedReadyTime;
    }
}
