package com.example.customer_service.mapper;

import com.example.customer_service.domain.Customer;
import com.example.customer_service.domain.OrderHistory;
import com.example.customer_service.dto.CustomerInformation;
import com.example.customer_service.dto.CustomerRequest;
import com.example.customer_service.dto.CustomerResponse;
import com.example.customer_service.dto.OrderSummary;
import com.example.customer_service.repo.CustomerRepo;

import java.util.List;

public class Mapper {
    public static OrderSummary toOrderSummary(OrderHistory order) {
        return OrderSummary.builder()
                .quantity(order.getQuantity())
                .orderDate(order.getOrderDate())
                .price(order.getPrice())
                .productCode(order.getProductCode())
                .status(order.getOrderStatus())
                .build();
    }

    public static CustomerInformation toCustomerInformation(Customer customer, List<OrderSummary> orders) {
        return CustomerInformation.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .balance(customer.getBalance())
                .shippingAddress(customer.getShippingAddress())
                .orderSummaries(orders)
                .build();
    }

    public static Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.name())
                .balance(customerRequest.balance())
                .shippingAddress(customerRequest.shippingAddress())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .balance(customer.getBalance())
                .shippingAddress(customer.getShippingAddress())
                .build();
    }
}
