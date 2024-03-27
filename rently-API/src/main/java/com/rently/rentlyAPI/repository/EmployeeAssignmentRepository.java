package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.EmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, Integer> {
}
