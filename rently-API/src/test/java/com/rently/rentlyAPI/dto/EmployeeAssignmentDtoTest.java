package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.AssignmentUpdate;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.EmployeeAssignment;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.enums.AssignmentStatus;
import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeAssignmentDtoTest {

    @Test
    void testFromEntity() {
        EmployeeAssignment employeeAssignment = new EmployeeAssignment();
        Company company = new Company();
        company.setId(1);
        employeeAssignment.setCompany(company);
        employeeAssignment.setId(1);
        Employee employee = new Employee();
        employee.setId(2);
        employeeAssignment.setEmployee(employee);
        OwnerRequest ownerRequest = new OwnerRequest();
        ownerRequest.setId(3);
        employeeAssignment.setOwnerRequest(ownerRequest);
        employeeAssignment.setWorkType(WorkType.GENERAL);
        employeeAssignment.setStatus(AssignmentStatus.ASSIGNED);

        List<AssignmentUpdate> assignmentUpdate = new ArrayList<>();
        AssignmentUpdate update1 = new AssignmentUpdate();
        update1.setStatus(AssignmentStatus.ASSIGNED);
        assignmentUpdate.add(update1);
        AssignmentUpdate update2 = new AssignmentUpdate();
        update2.setStatus(AssignmentStatus.ASSIGNED);
        assignmentUpdate.add(update2);

        employeeAssignment.setUpdates(assignmentUpdate);

        EmployeeAssignmentDto employeeAssignmentDto = EmployeeAssignmentDto.fromEntity(employeeAssignment);

        assertNotNull(employeeAssignmentDto);
        assertEquals(1, employeeAssignmentDto.getId());
        assertEquals(1, employeeAssignmentDto.getCompanyId());
        assertEquals(2, employeeAssignmentDto.getEmployeeId());
        assertEquals(3, employeeAssignmentDto.getOwnerRequestId());
        assertEquals("GENERAL", employeeAssignmentDto.getWorkType());
        assertEquals("ASSIGNED", employeeAssignmentDto.getStatus());
        assertEquals(2, employeeAssignmentDto.getAssignmentUpdates().size());
    }

    @Test
    void testToEntity() {
        EmployeeAssignmentDto employeeAssignmentDto = new EmployeeAssignmentDto(1, 1, 1, 1, "GENERAL", "ASSIGNED", new ArrayList<>());


        EmployeeAssignment employeeAssignment = EmployeeAssignmentDto.toEntity(employeeAssignmentDto);

        assertNotNull(employeeAssignment);
        assertEquals(1, employeeAssignment.getId());
        assertEquals(WorkType.GENERAL, employeeAssignment.getWorkType());
        assertEquals(AssignmentStatus.ASSIGNED, employeeAssignment.getStatus());
    }
}
