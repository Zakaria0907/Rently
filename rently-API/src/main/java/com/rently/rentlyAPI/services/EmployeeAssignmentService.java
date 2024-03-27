package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.entity.OwnerRequest;

import java.util.List;

public interface EmployeeAssignmentService {
    void createEmployeeAssignment(OwnerRequest savedOwnerRequest);

    List<EmployeeAssignmentDto> getAllEmployeeAssignmentsByCompanyId(Integer companyId);
}
