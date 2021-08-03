package com.pragmatest.nolt.end2end.tests.common.services;

import com.pragmatest.nolt.end2end.tests.common.models.AcceptOrderRequest;
import com.pragmatest.nolt.end2end.tests.common.models.AcceptOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestaurantOrderService {

    @Value(value = "${restaurant-service.accept-restaurant-order.template}")
    String acceptOrderUrl;

    @Autowired
    RestTemplate restTemplate;

    public void acceptOrder(String restaurantId, String orderId, AcceptOrderRequest acceptOrderRequest) {
        String url = acceptOrderUrl;

        HttpEntity<AcceptOrderRequest> request = new HttpEntity(acceptOrderRequest, getDefaultRequestHeaders(restaurantId));

        restTemplate.exchange(
                url, HttpMethod.PUT,
                request,AcceptOrderResponse.class,
                orderId);
    }

    private HttpHeaders getDefaultRequestHeaders(String restaurantId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Restaurant-Id", restaurantId);
        return httpHeaders;
    }
}
