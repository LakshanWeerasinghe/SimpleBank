package com.cse.database.dao.savings.account;

import com.cse.database.models.savings.account.SavingsAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SavingsAccountTypeDAO extends JpaRepository<SavingsAccountType, String> {

    Optional<SavingsAccountType> findByTypeName(@Param("type_name") String type);
}
