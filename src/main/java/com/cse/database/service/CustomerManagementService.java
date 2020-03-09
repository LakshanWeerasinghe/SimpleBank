package com.cse.database.service;

import com.cse.database.models.customer.Customer;

import java.util.Optional;

public interface CustomerManagementService {

    Optional<Customer> addNewCustomer(Customer customer);
}
