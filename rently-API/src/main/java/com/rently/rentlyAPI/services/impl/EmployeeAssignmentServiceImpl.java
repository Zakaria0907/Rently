package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.repository.EmployeeAssignmentRepository;
import com.rently.rentlyAPI.services.AssignmentUpdateService;
import com.rently.rentlyAPI.services.EmployeeAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeAssignmentServiceImpl implements EmployeeAssignmentService {
    private final EmployeeAssignmentRepository employeeAssignmentRepository;
    private final AssignmentUpdateService assignmentUpdateService;


    @Override
    public void createEmployeeAssignment(OwnerRequest savedOwnerRequest) {
        List<AssignmentUpdate> updates = new ArrayList<>();

        //create the assignment
        EmployeeAssignment employeeAssignment = EmployeeAssignment.builder()
                .company(savedOwnerRequest.getCompany())
                .workType(savedOwnerRequest.getWorkType())
                .ownerRequest(savedOwnerRequest)
                .build();

        //create the assignment update
        AssignmentUpdate assignmentUpdate = assignmentUpdateService.createAssignmentUpdateOnCreateAssignment(employeeAssignment);
        //save the add the update to the list in the assignment
        updates.add(assignmentUpdate);
        employeeAssignment.setUpdates(updates);

        employeeAssignmentRepository.save(employeeAssignment);
        assignmentUpdateService.save(assignmentUpdate);
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
        AssignmentUpdate assignmentUpdate = assignmentUpdateService.createAssignmentUpdateOnAssign(employeeAssignment);
        employeeAssignment.getUpdates().add(assignmentUpdate);

        EmployeeAssignment savedEmployeeAssignment = employeeAssignmentRepository.save(employeeAssignment);
        assignmentUpdateService.save(assignmentUpdate);
        return EmployeeAssignmentDto.fromEntity(savedEmployeeAssignment);
    }

    @Override
    public List<EmployeeAssignmentDto> getAllUnassignedEmployeeAssignmentsByCompanyId(Integer companyId) {
        List<EmployeeAssignmentDto> employeeAssignments = getAllEmployeeAssignmentsByCompanyId(companyId);//getAllEmployeeAssignmentsByCompanyId(companyId);
        return employeeAssignments.stream()
//                .map(EmployeeAssignmentDto::fromEntity)
                .filter(employeeAssignmentDto -> employeeAssignmentDto.getEmployeeId() == null)
                .toList();
    }

    @Override
    public List<EmployeeAssignmentDto> getAllAssignments(Integer id) {
        List<EmployeeAssignment> employeeAssignments = employeeAssignmentRepository.findAllByEmployeeId(id);
        return employeeAssignments.stream()
                .map(EmployeeAssignmentDto::fromEntity)
                .toList();
    }

    @Override
    public EmployeeAssignmentDto getAssignmentByEmployeeIdAndAssignmentId(Integer employeeId, Integer assignmentId) {
        Optional<EmployeeAssignment> employeeAssignment = employeeAssignmentRepository.findByEmployeeIdAndId(employeeId, assignmentId);
        if (employeeAssignment.isEmpty()) {
            throw new EntityNotFoundException("Employee assignment not found");
        }

        return EmployeeAssignmentDto.fromEntity(employeeAssignment.get());
    }


}
