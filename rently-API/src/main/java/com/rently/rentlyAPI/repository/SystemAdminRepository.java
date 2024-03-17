package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Integer> {
    Optional<SystemAdmin> findByEmail(String email);
}
