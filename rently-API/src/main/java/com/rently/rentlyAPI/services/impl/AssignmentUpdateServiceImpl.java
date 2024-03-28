package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.repository.AssignmentUpdateRepository;
import com.rently.rentlyAPI.services.AssignmentUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AssignmentUpdateServiceImpl implements AssignmentUpdateService {
    private final AssignmentUpdateRepository assignmentUpdateRepository;

    @Override
    public AssignmentUpdate createAssignmentUpdateOnCreateAssignment(EmployeeAssignment employeeAssignment) {

        return AssignmentUpdate.builder()
                .employeeAssignment(employeeAssignment)
                .status(AssignmentStatus.NOT_ASSIGNED)
                .creationDate(LocalDateTime.now())
                .comment("The assignment has been created, it will be assigned shortly.")
                .build();
    }

    @Override
    public void save(AssignmentUpdate assignmentUpdate) {
        assignmentUpdateRepository.save(assignmentUpdate);
    }

    @Override
    public AssignmentUpdate createAssignmentUpdateOnAssign(EmployeeAssignment employeeAssignment) {
        return AssignmentUpdate.builder()
                .employeeAssignment(employeeAssignment)
                .status(AssignmentStatus.ASSIGNED)
                .creationDate(LocalDateTime.now())
                .comment("An employee has been assigned to this assignment.")
                .build();
    }
}
