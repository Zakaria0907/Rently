package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.repository.AssignmentUpdateRepository;
import com.rently.rentlyAPI.services.impl.AssignmentUpdateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentUpdateServiceImplTest {

    @Mock
    private AssignmentUpdateRepository assignmentUpdateRepository;

    @InjectMocks
    private AssignmentUpdateServiceImpl assignmentUpdateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAssignmentUpdateOnCreateAssignment() {
        EmployeeAssignment employeeAssignment = new EmployeeAssignment();

        AssignmentUpdate expectedUpdate = AssignmentUpdate.builder()
                .employeeAssignment(employeeAssignment)
                .status(AssignmentStatus.NOT_ASSIGNED)
                .creationDate(LocalDateTime.now())
                .comment("The assignment has been created, it will be assigned shortly.")
                .build();

        when(assignmentUpdateRepository.save(any())).thenReturn(expectedUpdate);

        AssignmentUpdate createdUpdate = assignmentUpdateService.createAssignmentUpdateOnCreateAssignment(employeeAssignment);

        assertEquals(expectedUpdate, createdUpdate);
        verify(assignmentUpdateRepository, times(1)).save(any());
    }

    @Test
    public void testCreateAssignmentUpdateOnAssign() {
        EmployeeAssignment employeeAssignment = new EmployeeAssignment();

        AssignmentUpdate expectedUpdate = AssignmentUpdate.builder()
                .employeeAssignment(employeeAssignment)
                .status(AssignmentStatus.ASSIGNED)
                .creationDate(LocalDateTime.now())
                .comment("An employee has been assigned to this assignment.")
                .build();

        when(assignmentUpdateRepository.save(any())).thenReturn(expectedUpdate);

        AssignmentUpdate createdUpdate = assignmentUpdateService.createAssignmentUpdateOnAssign(employeeAssignment);

        assertEquals(expectedUpdate, createdUpdate);
        verify(assignmentUpdateRepository, times(1)).save(any());
    }
}
