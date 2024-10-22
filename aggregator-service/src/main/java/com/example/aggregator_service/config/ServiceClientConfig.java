package com.example.aggregator_service.config;

import com.example.aggregator_service.client.CustomerServiceClient;
import com.example.aggregator_service.client.ProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServiceClientConfig {
    private WebClient createWebClient(String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    @Bean
    public CustomerServiceClient customerServiceClient(
            @Value("${customer.service.url}") String url){
        return new CustomerServiceClient(createWebClient(url));
    }

    @Bean
    public ProductServiceClient productServiceClient(
            @Value("${product.service.url}") String url){
        return new ProductServiceClient(createWebClient(url));
    }



}
