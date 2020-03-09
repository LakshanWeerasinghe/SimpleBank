package com.cse.database.models.savings.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "withdraw")
public class Withdrawal {

    @Id
    @Column(name = "withdraw_id", columnDefinition = "VARCHAR(36)")
    private UUID withdrawalId;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id" ,
            columnDefinition = "VARCHAR(36)")
    private Transaction transaction;
}
