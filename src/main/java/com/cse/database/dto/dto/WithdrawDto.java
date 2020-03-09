package com.cse.database.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WithdrawDto {

    private String accountNumber;

    private String empUsername;

    private Date date;

    private Double amount;
}
