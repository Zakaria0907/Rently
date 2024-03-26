package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.repository.EmployeeAssignmentRepository;
import com.rently.rentlyAPI.services.EmployeeAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeAssignmentServiceImpl implements EmployeeAssignmentService {
    private final EmployeeAssignmentRepository employeeAssignmentRepository;

    @Override
    public void createEmployeeAssignment(OwnerRequest savedOwnerRequest) {
        EmployeeAssignment employeeAssignment = EmployeeAssignment.builder()
                .company(savedOwnerRequest.getCompany())
                .ownerRequest(savedOwnerRequest)
                .build();
        employeeAssignmentRepository.save(employeeAssignment);
    }
}
