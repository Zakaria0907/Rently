package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
