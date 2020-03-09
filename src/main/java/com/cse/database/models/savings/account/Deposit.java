package com.cse.database.models.savings.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "deposit")
public class Deposit{

    @Id
    @Column(name = "deposit_id", columnDefinition = "VARCHAR(36)")
    private UUID depositId;

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id",
            columnDefinition = "VARCHAR(36)")
    private Transaction transaction;
}
