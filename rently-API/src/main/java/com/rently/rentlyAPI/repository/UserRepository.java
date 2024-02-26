package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  @Query(value = """
        select u from User u inner join Key k
        on u.id = k.user.id
        where k.key = :key
        """)
    User findUserByKey(String key);

}
