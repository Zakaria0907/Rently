package com.rently.rentlyAPI.repository;


import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

	Optional<Building> findById(Integer id);
	Optional<BuildingDto> findByName(String name);


	List<Building> findAllByCompanyId(Integer companyId);

}
