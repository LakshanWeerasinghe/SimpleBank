package com.cse.database.models.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable{

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String employeeId;

    @Column(length = 50, name = "username", unique = true)
    private String userName;

    @Column(length = 512)
    private String password;

    private boolean active;

    private String roles;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;


}
