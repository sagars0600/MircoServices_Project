package com.mircoservices.customer.service;

import com.mircoservices.customer.exception.CustomerNotFoundException;
import com.mircoservices.customer.feign.AccountFeign;
import com.mircoservices.customer.model.Account;
import com.mircoservices.customer.model.Customer;
import com.mircoservices.customer.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private RestTemplate restTemplate;


    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(String Id) {
        return customerRepository.findById(Id);
    }

    public Customer getCustomerById(String Id) {
        Optional<Customer> customer = customerRepository.findById(Id);

        if (customer.isPresent())
            return customer.get();
        else
            throw new CustomerNotFoundException("Please check Id");
    }

    public Customer UpdateById(String Id, Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(Id);

        if (customer1.isPresent()) {
            Customer customerData = customer1.get();
            customerData.setPhoneNumber(customer.getPhoneNumber());
            customerData.setEmail(customer.getEmail());
            return customerRepository.save(customerData);

        } else
            throw new CustomerNotFoundException("Please check the Id");

    }


    public String deleteById(String Id, List<Account> account) {
        Optional<Customer> customer = customerRepository.findById(Id);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customer1.setIsActive(Boolean.FALSE);
            customerRepository.save(customer1);
            for (Account account1 : account) {
                account1.setIsCustomerActive(Boolean.FALSE);
                HttpEntity<Account> httpEntity = new HttpEntity(account1);
                customer1.setIsActive(restTemplate.postForObject("http://account/AccountCreation/update", httpEntity, Boolean.class));

            }
        } else throw new CustomerNotFoundException("Please check the CustomerId");
        return "Customer Deleted Successfully";
    }
}
