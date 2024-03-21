package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CompanyTest {

    @Test
    public void testCompanyEntity() {
        List<Building> buildings = new ArrayList<>();

        List<HousingContract> housingContracts = new ArrayList<>();

        List<EmploymentContract> employmentContracts = new ArrayList<>();

        List<Request> requests = new ArrayList<>();

        List<EmployeeAssignment> employeeAssignments = new ArrayList<>();

        List<CompanyAdmin> companyAdmins = new ArrayList<>();

        List<Employee> employees = new ArrayList<>();

        Company company = new Company();
        company.setId(1);
        company.setName("Test Company");
        company.setBuildings(buildings);
        company.setHousingContracts(housingContracts);
        company.setEmploymentContracts(employmentContracts);
        company.setRequests(requests);
        company.setEmployeeAssignments(employeeAssignments);
        company.setCompanyAdmins(companyAdmins);
        company.setEmployees(employees);

        assertNotNull(company);

        assertEquals(1, company.getId());
        assertEquals("Test Company", company.getName());
        assertEquals(buildings, company.getBuildings());
        assertEquals(housingContracts, company.getHousingContracts());
        assertEquals(employmentContracts, company.getEmploymentContracts());
        assertEquals(requests, company.getRequests());
        assertEquals(employeeAssignments, company.getEmployeeAssignments());
        assertEquals(companyAdmins, company.getCompanyAdmins());
        assertEquals(employees, company.getEmployees());
    }
}
