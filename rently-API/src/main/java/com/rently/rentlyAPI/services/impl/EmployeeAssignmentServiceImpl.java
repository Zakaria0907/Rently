package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.repository.AssignmentUpdateRepository;
import com.rently.rentlyAPI.repository.EmployeeAssignmentRepository;
import com.rently.rentlyAPI.services.EmployeeAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeAssignmentServiceImpl implements EmployeeAssignmentService {
    private final EmployeeAssignmentRepository employeeAssignmentRepository;
    private final AssignmentUpdateRepository assignmentUpdateRepository;


    @Override
    public void createEmployeeAssignment(OwnerRequest savedOwnerRequest) {
        List<AssignmentUpdate> updates = new ArrayList<>();

        EmployeeAssignment employeeAssignment = EmployeeAssignment.builder()
                .company(savedOwnerRequest.getCompany())
                .workType(savedOwnerRequest.getWorkType())
                .ownerRequest(savedOwnerRequest)
                .build();

        AssignmentUpdate assignmentUpdate = AssignmentUpdate.builder()
                .employeeAssignment(employeeAssignment)
                .status(AssignmentStatus.NOT_ASSIGNED)
                .creationDate(LocalDateTime.now())
                .comment("The assignment has been created, it will be assigned shortly.")
                .build();

        updates.add(assignmentUpdate);
        employeeAssignment.setUpdates(updates);
        employeeAssignmentRepository.save(employeeAssignment);
        assignmentUpdateRepository.save(assignmentUpdate);


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
