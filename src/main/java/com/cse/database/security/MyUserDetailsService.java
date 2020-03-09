package com.cse.database.security;

import com.cse.database.dao.employee.UsersDAO;
import com.cse.database.models.employee.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsersDAO usersDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = usersDAO.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found" + userName));

        return user.map(MyUserDetails::new).get();
    }

}
