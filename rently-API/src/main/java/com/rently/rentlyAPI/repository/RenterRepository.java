package com.rently.rentlyAPI.repository;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RenterRepository extends JpaRepository<Renter, Integer> {
	
	Optional<Renter> findByEmail(String email);
	
	
}
