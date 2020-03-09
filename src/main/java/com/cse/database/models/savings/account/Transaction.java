package com.cse.database.models.savings.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id", columnDefinition = "VARCHAR(36)")
    private UUID transactionId;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @Column(name = "amount", columnDefinition = "Decimal(10,2)")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "trans_type")
    private TransactionType transactionType;

    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

}
