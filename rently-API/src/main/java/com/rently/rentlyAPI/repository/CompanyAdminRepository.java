package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyAdminRepository extends JpaRepository<CompanyAdmin, Integer> {

    Optional<CompanyAdmin> findByEmail(String email);
    
    List<CompanyAdmin> findAllByCompanyId(Integer companyId);
    
    List<CompanyAdmin> findAllByCompanyName(String companyName);
}