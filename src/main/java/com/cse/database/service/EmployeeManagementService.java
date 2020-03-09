package com.cse.database.service;

import com.cse.database.dto.dto.UserDto;
import com.cse.database.models.branch.Branch;

import java.util.Optional;

public interface EmployeeManagementService {

    UserDto addNewEmployee(UserDto userDto);

    Optional<Branch> getBranchById(String branchId);
}
