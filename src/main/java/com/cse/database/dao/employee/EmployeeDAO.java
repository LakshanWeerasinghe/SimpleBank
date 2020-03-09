package com.cse.database.dao.employee;

import com.cse.database.models.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface EmployeeDAO extends JpaRepository<Employee, String> {

    Optional<Employee> findByNic(@Param("nic") String nic);

}
