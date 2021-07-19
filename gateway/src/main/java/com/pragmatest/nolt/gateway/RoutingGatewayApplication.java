package com.pragmatest.nolt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
public class RoutingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutingGatewayApplication.class, args);
    }
}
