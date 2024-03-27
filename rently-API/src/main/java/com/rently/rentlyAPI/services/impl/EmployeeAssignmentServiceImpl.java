package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.repository.EmployeeAssignmentRepository;
import com.rently.rentlyAPI.services.EmployeeAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeAssignmentServiceImpl implements EmployeeAssignmentService {
    private final EmployeeAssignmentRepository employeeAssignmentRepository;


    @Override
    public void createEmployeeAssignment(OwnerRequest savedOwnerRequest) {
        EmployeeAssignment employeeAssignment = EmployeeAssignment.builder()
                .company(savedOwnerRequest.getCompany())
                .workType(savedOwnerRequest.getWorkType())
                .ownerRequest(savedOwnerRequest)
                .build();
        employeeAssignmentRepository.save(employeeAssignment);
    }

    @Override
    public List<EmployeeAssignmentDto> getAllEmployeeAssignmentsByCompanyId(Integer companyId) {
        return employeeAssignmentRepository.findAllByCompanyId(companyId).stream()
                .map(EmployeeAssignmentDto::fromEntity)
                .toList();
    }

    @Override
    public EmployeeAssignmentDto assignEmployeeToAssignment(Employee employee, Integer assignmentId) {
        EmployeeAssignment employeeAssignment = employeeAssignmentRepository.findById(assignmentId).orElseThrow();
        employeeAssignment.setEmployee(employee);
        EmployeeAssignment savedEmployeeAssignment = employeeAssignmentRepository.save(employeeAssignment);
        return EmployeeAssignmentDto.fromEntity(savedEmployeeAssignment);
    }

    @Override
    public List<EmployeeAssignmentDto> getAllUnassignedEmployeeAssignmentsByCompanyId(Integer companyId) {
        List<EmployeeAssignmentDto> employeeAssignments = getAllEmployeeAssignmentsByCompanyId(companyId);
        return employeeAssignments.stream()
                .filter(employeeAssignmentDto -> employeeAssignmentDto.getEmployeeId() == null)
                .toList();
    }


}
