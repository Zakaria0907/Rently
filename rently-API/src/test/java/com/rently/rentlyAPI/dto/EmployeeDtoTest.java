package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

//@SpringBootTest
public class EmployeeDtoTest {

    @Test
    public void testToEntity() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .email("employee@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("Employee bio")
                .companyId(1)
                .employeeType(WorkType.GENERAL.name())
                .build();

        Employee employee = EmployeeDto.toEntity(employeeDto);

        assertEquals(employeeDto.getEmail(), employee.getEmail());
        assertEquals(employeeDto.getPassword(), employee.getPassword());
        assertEquals(employeeDto.getFirstName(), employee.getFirstName());
        assertEquals(employeeDto.getLastName(), employee.getLastName());
        assertEquals(employeeDto.getPhoneNumber(), employee.getPhoneNumber());
        assertEquals(employeeDto.getBio(), employee.getBio());
        assertEquals(Role.EMPLOYEE, employee.getRole());
    }

    @Test
    public void testFromEntity() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmail("employee@example.com");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPhoneNumber("1234567890");
        employee.setBio("Employee bio");
        employee.setRole(Role.EMPLOYEE);
        employee.setEmployeeType(WorkType.GENERAL);

        Company company = mock(Company.class);
        employee.setCompany(company);

        EmployeeDto employeeDto = EmployeeDto.fromEntity(employee);

        assertEquals(employee.getId(), employeeDto.getId());
        assertEquals(employee.getEmail(), employeeDto.getEmail());
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        assertEquals(employee.getLastName(), employeeDto.getLastName());
        assertEquals(employee.getPhoneNumber(), employeeDto.getPhoneNumber());
        assertEquals(employee.getBio(), employeeDto.getBio());
        assertEquals(employee.getRole().name(), employeeDto.getRole());
        assertEquals(employee.getCompany().getId(), employeeDto.getCompanyId());
    }
}
