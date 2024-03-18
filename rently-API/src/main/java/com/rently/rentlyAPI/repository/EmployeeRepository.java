package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findAllByCompanyId(Integer companyId);
}
