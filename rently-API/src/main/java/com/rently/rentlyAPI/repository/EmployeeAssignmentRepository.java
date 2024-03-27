package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.EmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, Integer> {
    List<EmployeeAssignment> findAllByCompanyId(Integer companyId);
}
