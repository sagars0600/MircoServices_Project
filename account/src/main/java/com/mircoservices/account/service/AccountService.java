package com.mircoservices.account.service;

import com.mircoservices.account.exceptions.CustomerNotActiveException;
import com.mircoservices.account.exceptions.CustomerNotFoundException;
import com.mircoservices.account.model.Account;
import com.mircoservices.account.model.AccountType;
import com.mircoservices.account.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    List<Account> accountList = new ArrayList<>();

    Account account;

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {

        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        accountList = accountRepository.findAll();
        return accountList;
    }

    public Optional<Account> getAccountById(Integer accountId) {
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        return account;
    }

    public Account createAccountNext(Account account) {

        return accountRepository.save(account);
    }

    public List<Account> getAccountsByCustomerId(String customerId) {
        return accountRepository.findAllByCustomerId(customerId);

    }

    public Boolean getAccountByCustomerId(String customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        //below if checks whether the id passed is valid
        if (accounts.isEmpty()) {
            throw new CustomerNotFoundException("Please check the Customer ID");
        }
        for (Account account : accounts) {
            //below if checks whether any account is active for this customer
            if (account.getIsCustomerActive().equals(Boolean.FALSE))
                throw new CustomerNotActiveException("Customer is Not Active");

        }
        return true;
    }

    public Boolean isActive(String customerId) {
        List<Account> accounts = new ArrayList<>(accountRepository.findByCustomerId(customerId));
        if (accounts.isEmpty()) {
            return false;
        }
        for (Account account : accounts) {
            if (Boolean.TRUE.equals(account.getIsCustomerActive()))
                return true;
        }
        return false;
    }

    public Boolean isAccountTypeAlreadyExist(String customerId, AccountType accountType) {

        List<Account> accountTypeList = new ArrayList<>(accountRepository.findByCustomerId(customerId));
        for (Account account : accountTypeList) {
            if (account.getAccountType() == accountType)
                return true;
        }
        return false;
    }
}
