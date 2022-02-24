package com.mircoservices.account.repo;

import com.mircoservices.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomerId(String customerId);

    List<Account> findAllByCustomerId(String customerId);

    Optional<Account> findByAccountId(Integer accountId);
}
