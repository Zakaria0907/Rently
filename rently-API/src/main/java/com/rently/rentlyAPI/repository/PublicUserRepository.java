package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.user.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicUserRepository extends JpaRepository<PublicUser, Integer> {

    PublicUser findByUsername(String username);

    PublicUser findByEmail(String email);

    List<PublicUser> findByFirstNameAndLastName(String firstName, String lastName);

    boolean isPresent (String email);
    // Custom query example
//    @Query("SELECT u FROM PublicUser u WHERE u.role =: publicUser")
//    List<PublicUser> findallPublicUsers(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

}
