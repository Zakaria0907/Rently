package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.user.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class EmployeeAssignmentTest {
    Company mockedCompany = mock(Company.class);
    Employee mockedEmployee = mock(Employee.class);
    OwnerRequest mockedOwnerRequest = mock(OwnerRequest.class);

    @Test
    public void testAllArgsConstructor() {

        EmployeeAssignment testEmployeeAssignment = new EmployeeAssignment(mockedCompany, mockedEmployee, mockedOwnerRequest);
        assertEquals(mockedCompany, testEmployeeAssignment.getCompany());
        assertEquals(mockedEmployee, testEmployeeAssignment.getEmployee());
        assertEquals(mockedOwnerRequest, testEmployeeAssignment.getOwnerRequest());
    }
}
