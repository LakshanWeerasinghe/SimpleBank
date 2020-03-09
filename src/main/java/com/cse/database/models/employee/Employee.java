package com.cse.database.models.employee;

import com.cse.database.models.branch.Branch;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "employee", indexes = @Index(name = "nic", columnList = "nic", unique = true))
public class Employee {

    @Id
    @Column(name = "employee_id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    @Size(max = 10, min = 10)
    @Column(nullable = false)
    private String nic;

    @ManyToOne
    @JoinColumn(name = "branch_id", columnDefinition = "VARCHAR(50)", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT), referencedColumnName = "branch_id")
    private Branch branch;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User users;


}
