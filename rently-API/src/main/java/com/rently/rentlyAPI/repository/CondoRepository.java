package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.Condo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CondoRepository extends JpaRepository<Condo, Integer> {

}
