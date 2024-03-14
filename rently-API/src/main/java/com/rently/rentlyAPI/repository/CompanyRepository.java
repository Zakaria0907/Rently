package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    void deleteCompanyById(Integer companyId);

    void deleteCompanyByName(String name);

    Optional<Company> findCompanyByName(String name);
}
