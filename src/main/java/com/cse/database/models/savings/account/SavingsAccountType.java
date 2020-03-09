package com.cse.database.models.savings.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savings_account_type")
public class SavingsAccountType {

    @Id
    @Column(name = "account_type_id")
    private String typeId;

    @Column(name = "type_name", length = 30, nullable = false)
    private String typeName;

    @Column(name = "interest_rate", columnDefinition = "Decimal(4,2)", nullable = false)
    private Double interestRate;

    @Column(name = "min_balance", columnDefinition = "Decimal(10,2)", nullable = false)
    private Double minBalance;

    @Column(name = "min_age", columnDefinition = "Integer(2)", nullable = false)
    private Integer minAge;

    @Column(name = "max_withdraws", columnDefinition = "Integer(3)", nullable = false)
    private Integer maximumWithdraws;

}
