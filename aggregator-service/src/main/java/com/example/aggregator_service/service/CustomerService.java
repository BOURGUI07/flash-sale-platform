package com.example.aggregator_service.service;

import com.example.aggregator_service.client.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerServiceClient client;

    
}
