package com.cse.database.models.savings.account;

import com.cse.database.models.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savings_account")
public class SavingsAccount {

    @Id
    @Column(name = "account_no", columnDefinition = "CHAR(16)")
    private String accountNumber;

    @Column(columnDefinition = "Decimal(10,2)", nullable = false)
    private Double balance;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    private List<Customer> owners;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "account_type_id")
    private SavingsAccountType accountType;

    public enum Status {
       OPEN, CLOSED
    }

}
