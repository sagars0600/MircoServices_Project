package com.mircoservices.account.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    LocalDateTime dateTime;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private String customerId;
    private Boolean isCustomerActive;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "AccountType can not be null")
    private AccountType accountType;


}
