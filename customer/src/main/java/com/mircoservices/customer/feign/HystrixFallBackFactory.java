package com.mircoservices.customer.feign;

import org.springframework.cloud.openfeign.FallbackFactory;


public class HystrixFallBackFactory implements FallbackFactory<AccountFeign> {

    public AccountFeign create(Throwable cause) {
        return id -> {
            System.out.println("fallback; reason was: " + cause.getMessage());
            return null;
        };
    }
}
