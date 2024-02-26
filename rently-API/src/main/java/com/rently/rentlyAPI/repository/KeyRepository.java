package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Integer> {

    Key findByKey(String key);

}
