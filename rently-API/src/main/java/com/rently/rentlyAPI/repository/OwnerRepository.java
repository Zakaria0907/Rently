package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
	
	Optional<Owner> findByEmail(String email);
	
}
