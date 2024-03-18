package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.entity.user.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto registerEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId);

    Optional<Employee> findByEmail(String email);
}
