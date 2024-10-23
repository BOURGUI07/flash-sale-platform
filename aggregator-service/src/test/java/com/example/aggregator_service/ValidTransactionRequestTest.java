package com.example.aggregator_service;

import com.example.aggregator_service.domain.OrderStatus;
import com.example.aggregator_service.domain.ProductCategory;
import com.example.aggregator_service.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ValidTransactionRequestTest extends AbstractTest {
    @Test
    void buyAndCancel(){
        var request = PurchaseRequest.builder()
                        .productCode("P003")
                                .desiredQuantity(1)
                                        .build();
        client
                .post()
                .uri("/transactions/{customerId}/buy",3)
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(CustomerPurchaseResponse.class)
                .getResponseBody()
                .doOnNext(response -> log.info(response.toString()))
                .as(StepVerifier::create)
                .assertNext(response -> {
                    assertEquals(ProductCategory.FURNITURE,response.productCategory());
                    assertEquals(1,response.quantity());
                    assertEquals("Charlie",response.customerName());
                    assertEquals("P003",response.productCode());
                    assertTrue(response.balance()<2000);
                    assertEquals(OrderStatus.PENDING,response.orderStatus());
                })
                .verifyComplete();


            var cancelPurchaseRequest = CancelPurchaseRequest.builder()
                .orderId(3)
                .build();
        client
                .post()
                .uri("/transactions/{customerId}/cancel",3)
                .bodyValue(cancelPurchaseRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(CancelPurchaseResponse.class)
                .getResponseBody()
                .as(StepVerifier::create)
                .assertNext(response -> {
                    assertEquals(OrderStatus.CANCELLED,response.orderStatus());
                    assertEquals(1,response.returnedQuantity());
                })
                .verifyComplete();


    }




}
