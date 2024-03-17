package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PublicUserRepository extends JpaRepository<PublicUser, Integer> {

    Optional<PublicUser> findByUsername(String username);

    Optional<PublicUser> findByEmail(String email);

    List<PublicUser> findByFirstNameAndLastName(String firstName, String lastName);

//    // Custom query example
//    @Query("SELECT u FROM PublicUser u WHERE u.age >= :minAge AND u.age <= :maxAge")
//    List<PublicUser> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

}
