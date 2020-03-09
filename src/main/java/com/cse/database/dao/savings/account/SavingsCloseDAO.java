package com.cse.database.dao.savings.account;


import com.cse.database.models.savings.account.SavingsClose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsCloseDAO extends JpaRepository<SavingsClose, String>{
}

