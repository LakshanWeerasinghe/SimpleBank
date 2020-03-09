package com.cse.database.dao.branch;

import com.cse.database.models.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchDAO extends JpaRepository<Branch,String> {
}
