package com.example.product_service.util;

import com.github.javafaker.Faker;

public class Util {
    private static final Faker faker = Faker.instance();

    public static Faker faker() {
        return faker;
    }
}
