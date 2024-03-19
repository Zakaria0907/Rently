package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.CommonFacility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonFacilityRepository extends JpaRepository<CommonFacility, Integer> {
    Optional<CommonFacility> findByName(String facilityName);
}
