package com.cse.database.dao.customer;

import com.cse.database.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CustomerDAO extends JpaRepository<Customer, String> {

    Optional<Customer> findCustomerByNic(@Param("nic") String nic);
}
