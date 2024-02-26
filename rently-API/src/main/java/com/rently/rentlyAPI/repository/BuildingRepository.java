package com.rently.rentlyAPI.repository;


import com.rently.rentlyAPI.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
	
	// Returns all buildings by company id
	List<Building> findAllByCompanyId(Integer companyId);
	
//	// Returns the number of condos in a building
//	@Query("SELECT COUNT(c) FROM Condo c WHERE c.building.id = :buildingId")
//	Integer countCondosById(Integer buildingId);
//
//	// Returns all condos in a building
//	@Query("SELECT c FROM Condo c WHERE c.building.id = :buildingId")
//	List<Condo> findAllCondosById(Integer buildingId);

}
