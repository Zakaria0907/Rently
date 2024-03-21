package com.rently.rentlyAPI.repository;

//import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.entity.Condo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface CondoRepository extends JpaRepository<Condo, Integer> {

	//	// Returns the number of condos in a building
//	@Query("SELECT COUNT(c) FROM Condo c WHERE c.building.id = :buildingId")
//	Integer countCondosById(Integer buildingId);

//	// Returns all condos in a building
//	@Query("SELECT c FROM Condo c WHERE c.building.id = :buildingId")
//	List<Condo> findAllCondosById(Integer buildingId)

	Integer countAllByBuildingId(Integer buildingId);

	Optional<Condo> findCondoByRegistrationKey(String registrationKey);

	List<Condo> findAllByBuildingId(Integer buildingId);
	
	Optional<Condo> findByUnitNumberAndBuildingId(Integer unitNumber, Integer buildingId);
	
	boolean existsByRegistrationKey(String registrationKey);
	

}
