package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.HousingContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HousingContractRepository extends JpaRepository<HousingContract, Integer> {
	
	Optional<HousingContract> findByCondoIdAndOccupantId(Integer condo, Integer occupant);
	Optional<HousingContract> findByCondoId(Integer condo);

}
