package com.pragmatest.nolt.customers.configuration;

import com.pragmatest.nolt.customers.service.models.Order;
import com.pragmatest.nolt.customers.web.requests.OrderRequest;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        addOrderRequestToOrderMapping(modelMapper);


        return modelMapper;
    }

    private void addOrderRequestToOrderMapping(ModelMapper modelMapper) {
        PropertyMap<OrderRequest, Order> skipFieldsMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                skip().setOrderId(null);
            }
        };

        modelMapper.addMappings(skipFieldsMap);
    }
}
