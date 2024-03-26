package com.rently.rentlyAPI.repository;

import com.rently.rentlyAPI.entity.OwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OwnerRequestRepository extends JpaRepository<OwnerRequest, Integer> {
    Collection<OwnerRequest> findAllByOwnerId(Integer ownerId);

    OwnerRequest findByOwnerIdAndId(Integer occupantId, Integer requestId);
}
