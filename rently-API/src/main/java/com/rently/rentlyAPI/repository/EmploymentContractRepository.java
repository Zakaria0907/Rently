package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.EmploymentContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Integer> {
    List<EmploymentContract> findByCompanyIdAndBuildingId(Integer companyId, Integer buildingId);
}
