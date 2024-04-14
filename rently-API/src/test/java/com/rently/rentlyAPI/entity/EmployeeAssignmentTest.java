package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

//@SpringBootTest
public class EmployeeAssignmentTest {
    Company mockedCompany = mock(Company.class);
    Employee mockedEmployee = mock(Employee.class);
    OwnerRequest mockedOwnerRequest = mock(OwnerRequest.class);
    List<AssignmentUpdate> nullList = null;

    @Test
    public void testAllArgsConstructor() {

        EmployeeAssignment testEmployeeAssignment = new EmployeeAssignment(mockedCompany, mockedEmployee, mockedOwnerRequest, WorkType.GENERAL, AssignmentStatus.NOT_ASSIGNED, nullList);
        assertEquals(mockedCompany, testEmployeeAssignment.getCompany());
        assertEquals(mockedEmployee, testEmployeeAssignment.getEmployee());
        assertEquals(mockedOwnerRequest, testEmployeeAssignment.getOwnerRequest());
    }
}
