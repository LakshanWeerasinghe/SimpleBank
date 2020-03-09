package com.cse.database.service;

import com.cse.database.dao.customer.CustomerDAO;
import com.cse.database.exception.BRSException;
import com.cse.database.models.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerManagementServiceImp implements CustomerManagementService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Optional<Customer> addNewCustomer(Customer customer) {

        Optional<Customer> customerOptional = customerDAO.findCustomerByNic(customer.getNic());

        if(!customerOptional.isPresent()){
            return Optional.of(customerDAO.saveAndFlush(customer));
        }
        throw new BRSException.DuplicateEntityException("Customer Already Exist");
    }
}
