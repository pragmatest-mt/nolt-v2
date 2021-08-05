package com.pragmatest.nolt.e2e.tests.common.services.restaurants;

import com.pragmatest.nolt.contracts.customers.AcceptOrderRequest;
import com.pragmatest.nolt.contracts.customers.AcceptOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@ConditionalOnProperty(value = "stubs.restaurants.enabled", havingValue = "false", matchIfMissing = true)
public class RestaurantOrderServiceImpl implements ResturantsOrderService {

    @Value(value = "${restaurant-service.accept-restaurant-order.template}")
    String acceptOrderUrl;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<AcceptOrderResponse> acceptOrder(String restaurantId, String orderId, AcceptOrderRequest acceptOrderRequest) {
        String url = acceptOrderUrl;

        HttpEntity<AcceptOrderRequest> request = new HttpEntity(acceptOrderRequest, getDefaultRequestHeaders(restaurantId));

        ResponseEntity<AcceptOrderResponse> acceptOrderResponseResponseEntity = restTemplate.exchange(
                url, HttpMethod.PUT,
                request, AcceptOrderResponse.class,
                orderId);

        return acceptOrderResponseResponseEntity;
    }

    private HttpHeaders getDefaultRequestHeaders(String restaurantId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Restaurant-Id", restaurantId);
        return httpHeaders;
    }
}
