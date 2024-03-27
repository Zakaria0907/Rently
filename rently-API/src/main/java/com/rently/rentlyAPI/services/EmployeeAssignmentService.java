package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Employee;

import java.util.List;

public interface EmployeeAssignmentService {
    void createEmployeeAssignment(OwnerRequest savedOwnerRequest);

    List<EmployeeAssignmentDto> getAllEmployeeAssignmentsByCompanyId(Integer companyId);

    EmployeeAssignmentDto assignEmployeeToAssignment(Employee employee, Integer assignmentId);

    List<EmployeeAssignmentDto> getAllUnassignedEmployeeAssignmentsByCompanyId(Integer companyId);
}
