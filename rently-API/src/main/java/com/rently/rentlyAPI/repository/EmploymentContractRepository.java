package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.EmploymentContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Integer> {
}
