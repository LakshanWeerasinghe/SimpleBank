package com.cse.database.controllers.api;

import com.cse.database.controllers.request.customer.AddNewCustomerRequest;
import com.cse.database.exception.BRSException;
import com.cse.database.models.customer.Customer;
import com.cse.database.models.savings.account.SavingsAccount;
import com.cse.database.service.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerManagementService customerManagementService;


    @PostMapping
    public ResponseEntity addNewCustomer(@RequestBody @Valid AddNewCustomerRequest addNewCustomerRequest){


        Map<String, Object> responseBody = new HashMap<>();

        ArrayList<String> errors = new ArrayList<>();

        Customer customer = new Customer()
                                    .setId(UUID.randomUUID().toString())
                                    .setFirstName(addNewCustomerRequest.getFirstName())
                                    .setLastName(addNewCustomerRequest.getLastName())
                                    .setNic(addNewCustomerRequest.getNic())
                                    .setDob(addNewCustomerRequest.getDob());

        try {

            Optional<Customer> customerOptional = customerManagementService.addNewCustomer(customer);

            if(customerOptional.isPresent()){

                responseBody.put("Success", "Customer Added Successfully");
                return ResponseEntity.ok().body(responseBody);
            }
            errors.add("Customer Creation Failed Try Again!");

        }
        catch (BRSException.DuplicateEntityException ex){
            errors.add(ex.getMessage());
        }

        responseBody.put("errors", errors);

        return ResponseEntity.badRequest().body(responseBody);
    }
}
