package com.example.product_service.controller;

import com.example.product_service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping(("/purchase-request"))
    public Mono<PurchaseResponse> getProductInformation(
            @RequestBody Mono<PurchaseRequest> request
    ){

    }

    @PostMapping("/process-request")
    public Mono<ProductPurchaseProcessRequest> processProductPurchase(
            @RequestBody Mono<ProductPurchaseProcessResponse> request
    ){

    }

    @PostMapping
    public Mono<ProductResponse> createProduct(
            @RequestBody Mono<ProductRequest> request
    ){

    }

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductResponse> getProductStream(){

    }




}
