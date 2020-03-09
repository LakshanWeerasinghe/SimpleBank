package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionDAO extends JpaRepository<Transaction, UUID> {
}
