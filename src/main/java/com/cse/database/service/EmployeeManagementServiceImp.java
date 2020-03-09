package com.cse.database.service;

import com.cse.database.dao.branch.BranchDAO;
import com.cse.database.dao.employee.EmployeeDAO;
import com.cse.database.dao.employee.UsersDAO;
import com.cse.database.dto.dto.UserDto;
import com.cse.database.exception.BRSException;
import com.cse.database.models.branch.Branch;
import com.cse.database.models.employee.Employee;
import com.cse.database.models.employee.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class EmployeeManagementServiceImp implements EmployeeManagementService{

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private UsersDAO usersDAO;



    @Transactional
    @Override
    public UserDto addNewEmployee(UserDto userDto){


        Optional<Employee> employee = employeeDAO.findByNic(userDto.getNic());

        if(!employee.isPresent()){
            Employee emp = new Employee()
                            .setId(userDto.getId())
                            .setFirstName(userDto.getFirstName())
                            .setLastName(userDto.getLastName())
                            .setNic(userDto.getNic())
                            .setDob(userDto.getDob())
                            .setBranch(userDto.getBranch());

            Optional<User> users = usersDAO.findByUserName(userDto.getUsername());

            if (!users.isPresent()){
                User user = new User()
                                    .setUserName(userDto.getUsername())
                                    .setPassword(userDto.getPassword())
                                    .setEmployee(emp)
                                    .setEmployeeId(userDto.getId())
                                    .setActive(true)
                                    .setRoles("ROLE_ADMIN");

                employeeDAO.save(emp);
                usersDAO.save(user);
                
                return userDto;

            }

            throw new BRSException.DuplicateEntityException("Username Already Exist!");

        }

       throw new BRSException.DuplicateEntityException("Employee Already Registered!");

    }

    @Override
    public Optional<Branch> getBranchById(String branchId) {
        return branchDAO.findById(branchId);

    }
}
