package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.EmployeeRepository;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId) {
        List<Employee> employees = employeeRepository.findAllByCompanyId(companyId);
        return employees.stream()
                .map(EmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee findById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id: " + employeeId + " not found"));
    }


}
