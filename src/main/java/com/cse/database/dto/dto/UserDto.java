package com.cse.database.dto.dto;

import com.cse.database.models.branch.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserDto {

    private String id;

    private String firstName;

    private String lastName;

    private String nic;

    private Date dob;

    private String username;

    private String password;

    private Branch branch;

}
