package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublicUserRepository extends JpaRepository<PublicUser, Integer> {

    Optional<PublicUser> findByEmail(String email);

    List<PublicUser> findByFirstNameAndLastName(String firstName, String lastName);

}
