package com.cse.database.controllers.request.savingsaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {

    private String accountNumber;

    private Double amount;

}
