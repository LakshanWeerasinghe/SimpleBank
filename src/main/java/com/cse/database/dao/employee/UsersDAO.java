package com.cse.database.dao.employee;

import com.cse.database.models.employee.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsersDAO extends JpaRepository<User, String>{

    Optional<User> findByUserName(@Param("username") String username);

}
