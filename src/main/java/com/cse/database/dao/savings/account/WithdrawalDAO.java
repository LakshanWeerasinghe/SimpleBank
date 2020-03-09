package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WithdrawalDAO extends JpaRepository<Withdrawal, UUID> {
}
