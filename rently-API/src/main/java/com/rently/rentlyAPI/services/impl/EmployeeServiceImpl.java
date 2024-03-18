package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.EmployeeRepository;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Override
    public EmployeeDto registerEmployee(EmployeeDto employeeDto) {
        // Check if the email is already associated with an account
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

        // If the email is already associated with an account, throw an exception
        if (existingEmployee.isPresent()) {
            throw new AuthenticationException("This email is already associated with an account");
        }

        // Retrieve company with the given ID
        Company companyToLink = companyService.findCompanyEntityById(employeeDto.getCompanyId());

        // Create the employee
        Employee employeeToSave = EmployeeDto.toEntity(employeeDto);

        // Link the employee to the company
        employeeToSave.setCompany(companyToLink);

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employeeToSave);

        // Return the employee dto
        return EmployeeDto.fromEntity(savedEmployee);
    }


}
