package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.EmploymentContract;
import com.rently.rentlyAPI.entity.user.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

//@SpringBootTest
public class EmploymentContractDtoTest {

    @Test
    public void testFromEntity() {
        EmploymentContract employmentContract = new EmploymentContract();
        employmentContract.setId(1);
        employmentContract.setYearlySalary(60000);
        employmentContract.setStartDate("2024-03-21");

        Company company = new Company();
        company.setId(1);

        Building building = new Building();
        building.setId(1);
        Employee employee = new Employee();

        employmentContract.setCompany(company);
        employmentContract.setBuilding(building);
        employmentContract.setEmployee(employee);

        EmploymentContractDto employmentContractDto = EmploymentContractDto.fromEntity(employmentContract);

        assertNotNull(employmentContractDto);
        assertEquals(employmentContract.getId(), employmentContractDto.getId());
        assertEquals(employmentContract.getCompany().getId(), employmentContractDto.getCompanyId());
        assertEquals(employmentContract.getBuilding().getId(), employmentContractDto.getBuildingId());
        assertEquals(employmentContract.getEmployee().getId(), employmentContractDto.getEmployeeId());
        assertEquals(employmentContract.getYearlySalary(), employmentContractDto.getYearlySalary());
        assertEquals(employmentContract.getStartDate(), employmentContractDto.getStartDate());
    }

    @Test
    public void testToEntity() {
        EmploymentContractDto employmentContractDto = mock(EmploymentContractDto.class);
        employmentContractDto.setId(1);
        employmentContractDto.setCompanyId(1);
        employmentContractDto.setBuildingId(1);
        employmentContractDto.setEmployeeId(1);
        employmentContractDto.setYearlySalary(60000);
        employmentContractDto.setStartDate("2024-03-21");

        EmploymentContract employmentContract = EmploymentContractDto.toEntity(employmentContractDto);

        assertNotNull(employmentContract);
        assertEquals(employmentContractDto.getId(), employmentContract.getId());
        assertEquals(employmentContractDto.getYearlySalary(), employmentContract.getYearlySalary());
        assertEquals(employmentContractDto.getStartDate(), employmentContract.getStartDate());
    }
}
