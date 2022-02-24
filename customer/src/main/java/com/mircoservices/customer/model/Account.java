package com.mircoservices.customer.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    LocalDateTime dateTime;
    private Integer accountId;
    private String customerId;
    private Boolean isCustomerActive;
    private AccountTypes accountTypes;
}
