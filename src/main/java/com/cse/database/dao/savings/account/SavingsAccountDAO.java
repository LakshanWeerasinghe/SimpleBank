package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SavingsAccountDAO extends JpaRepository<SavingsAccount, String> {

    Optional<SavingsAccount> findByAccountNumber(@Param("account_no") String accountNumber);
}
