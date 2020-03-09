package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositDAO extends JpaRepository<Deposit, UUID> {
}
