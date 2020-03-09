package com.cse.database.models.customer;

import com.cse.database.models.savings.account.SavingsAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id", length = 36, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "nic", unique = true, nullable = false)
    private String nic;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    @ManyToMany
    private List<SavingsAccount> accounts;
}
