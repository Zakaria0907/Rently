package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.entity.user.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto registerEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId);

    Optional<Employee> findByEmail(String email);

    Employee findById(Integer employeeId);

    Employee findEmployeeEntityByToken(String token);

    void deleteEmployee(Integer id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeesByTypeAndBuilding(Integer companyId, String employeeType, Integer buildingId);

    List<EmployeeAssignmentDto> getAllEmployeeAssignmentsByCompanyId(Integer companyId);

    List<EmployeeAssignmentDto> getAllUnassignedEmployeeAssignmentsByCompanyId(Integer companyId);

    List<EmployeeAssignmentDto> getAllAssignments(String token);

    EmployeeAssignmentDto getAssignmentById(String token, Integer assignmentId);
}
