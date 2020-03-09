package com.cse.database.controllers.request.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors( chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AddNewEmployeeRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dob;

    @NotEmpty(message = "Provide a nic number")
    private String nic;

    @NotEmpty
    private String branchId;

    @NotEmpty(message = "Provide a username")
    private String username;

    @NotEmpty
    private String password;
}
