package com.cse.database.models.savings.account;

import com.cse.database.models.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savings_close")
public class SavingsClose implements Serializable {

    @Id
    private String accountNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "closed_date")
    private Date closeDate;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id",
            foreignKey = @ForeignKey, columnDefinition = "VARCHAR(36)")
    private Employee closedBy;

    @MapsId
    @OneToOne
    @JoinColumn(referencedColumnName = "account_no", columnDefinition = "CHAR(16)")
    private SavingsAccount account;
}
