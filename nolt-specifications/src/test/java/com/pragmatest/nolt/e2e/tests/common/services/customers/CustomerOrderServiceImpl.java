package com.pragmatest.nolt.e2e.tests.common.services.customers;

import com.pragmatest.nolt.e2e.tests.common.models.customers.GetCustomerOrderResponse;
import com.pragmatest.nolt.e2e.tests.common.models.restaurants.SubmitCustomerOrderRequest;
import com.pragmatest.nolt.e2e.tests.common.models.restaurants.SubmitCustomerOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Value(value = "${customer-service.get-customer-order.template}")
    String getCustomerOrderUrl;

    @Value(value = "${customer-service.submit-customer-order.template}")
    String submitCustomerOrderUrl;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<GetCustomerOrderResponse> GetCustomerOrder(String customerId, String orderId) {
        String url = getCustomerOrderUrl;

        HttpEntity request = new HttpEntity(getDefaultRequestHeaders(customerId));

        ResponseEntity<GetCustomerOrderResponse> response = restTemplate.exchange(
                url, HttpMethod.GET,
                request, GetCustomerOrderResponse.class, orderId);


        return response;
    }

    public ResponseEntity<SubmitCustomerOrderResponse> SubmitCustomerOrder(String customerId, SubmitCustomerOrderRequest submitCustomerOrderRequest) {
        String url = submitCustomerOrderUrl;

        HttpEntity<SubmitCustomerOrderRequest> request = new HttpEntity(submitCustomerOrderRequest, getDefaultRequestHeaders(customerId));

        ResponseEntity<SubmitCustomerOrderResponse> response = restTemplate.exchange(
                url, HttpMethod.POST,
                request, SubmitCustomerOrderResponse.class);

        return response;
    }

    private HttpHeaders getDefaultRequestHeaders(String customerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Customer-Id", customerId);
        return httpHeaders;
    }
}
