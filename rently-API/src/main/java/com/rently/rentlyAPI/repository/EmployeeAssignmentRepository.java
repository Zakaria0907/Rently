package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.EmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, Integer> {
    List<EmployeeAssignment> findAllByCompanyId(Integer companyId);

    List<EmployeeAssignment> findAllByEmployeeId(Integer id);

    Optional<EmployeeAssignment> findByEmployeeIdAndId(Integer employeeId, Integer assignmentId);

    EmployeeAssignment findByOwnerRequestId(Integer id);
}
