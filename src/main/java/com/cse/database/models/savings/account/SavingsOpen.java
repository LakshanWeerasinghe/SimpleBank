package com.cse.database.models.savings.account;

import com.cse.database.models.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savings_open")
public class SavingsOpen implements Serializable {

    @Id
    private String accountNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "opened_date")
    private Date openedDate;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id",
            foreignKey = @ForeignKey, columnDefinition = "VARCHAR(36)")
    private Employee openBy;

    @MapsId
    @OneToOne
    @JoinColumn(referencedColumnName = "account_no", columnDefinition = "CHAR(16)")
    private SavingsAccount account;

}
