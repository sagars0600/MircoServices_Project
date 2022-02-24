package com.mircoservices.customer.feign;


import com.mircoservices.customer.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(name = "account", fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign {
    @GetMapping("/AccountCreation/getAccountById/Id/{Id}")
    public ResponseEntity<List<Account>> getAccountByCustomerId(@PathVariable String Id);
}
