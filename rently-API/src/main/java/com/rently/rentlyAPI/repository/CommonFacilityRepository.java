package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.CommonFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommonFacilityRepository extends JpaRepository<CommonFacility, Integer> {
    Optional<CommonFacility> findByName(String facilityName);

    @Query("SELECT c FROM CommonFacility c WHERE c.building.id = ?1 AND c.name = ?2")
    Optional<CommonFacility> findByNameAndBuildingId(Integer buildingId, String facilityName);
}
