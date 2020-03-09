package com.cse.database.service;

import com.cse.database.dto.dto.DepositDto;
import com.cse.database.dto.dto.WithdrawDto;
import com.cse.database.models.savings.account.SavingsAccount;
import com.cse.database.models.savings.account.SavingsClose;
import com.cse.database.models.savings.account.SavingsOpen;

import java.util.Optional;

public interface SavingsAccountService {

    Optional<SavingsAccount> openSavingsAccount(SavingsAccount savingsAccount, SavingsOpen savingsOpen,
                                                  String accountType, String employeeUsername, String customerNic);

    void closeSavingsAccount(String savingsAccountNumber, SavingsClose savingsClose,
                                    String employeeUsername);

    void deposit(DepositDto depositDto);

    void withdraw(WithdrawDto withdrawDto);
}
