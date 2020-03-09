package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.SavingsOpen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsOpenDAO extends JpaRepository<SavingsOpen, String>{
}
