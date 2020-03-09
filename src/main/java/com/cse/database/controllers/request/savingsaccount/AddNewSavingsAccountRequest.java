package com.cse.database.controllers.request.savingsaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AddNewSavingsAccountRequest {

    private String accountNumber;

    private String accountType;

    private Double amount;

    private String nic;

}
