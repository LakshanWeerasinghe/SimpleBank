package com.cse.database.controllers.api;

import com.cse.database.controllers.request.employee.AddNewEmployeeRequest;
import com.cse.database.dto.dto.UserDto;
import com.cse.database.exception.BRSException;
import com.cse.database.models.branch.Branch;
import com.cse.database.service.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @PostMapping("/addNew")
    public ResponseEntity addNewEmployee(@RequestBody @Valid AddNewEmployeeRequest addNewEmployeeRequest) {

        Map<String, Object> responseBody = new HashMap<>();

        ArrayList<String> errors = new ArrayList<>();

        Optional<Branch> branch = employeeManagementService.getBranchById(addNewEmployeeRequest.getBranchId());

        if (branch.isPresent()) {

            UserDto userDto = new UserDto()
                    .setId(UUID.randomUUID().toString())
                    .setFirstName(addNewEmployeeRequest.getFirstName())
                    .setLastName(addNewEmployeeRequest.getLastName())
                    .setDob(addNewEmployeeRequest.getDob())
                    .setNic(addNewEmployeeRequest.getNic())
                    .setBranch(branch.get())
                    .setUsername(addNewEmployeeRequest.getUsername())
                    .setPassword(addNewEmployeeRequest.getPassword());

            try {

                Optional<UserDto> dto = Optional.ofNullable(employeeManagementService.addNewEmployee(userDto));
                if(dto.isPresent()){
                    responseBody.put("Success", "Employee Added Successfully");
                    return ResponseEntity.ok().body(responseBody);
                }
                else {
                    errors.add("Employee Creation Failed Try Again!");
                }

            }catch (BRSException.DuplicateEntityException ex){
                errors.add(ex.getMessage());
            }
        }
        else {
            errors.add("Branch Id doesn't exist");
        }

        responseBody.put("errors", errors);

        return ResponseEntity.badRequest().body(responseBody);
    }
}
