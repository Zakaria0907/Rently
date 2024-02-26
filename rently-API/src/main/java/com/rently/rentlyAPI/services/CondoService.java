package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.Condo;

import java.util.List;

public interface CondoService {

    public Condo save(Condo condo);

    public boolean exists(Condo condo);
    
    // Returns the number of condos in a building
    public Integer countCondosByBuildingId(Integer companyId);
    
    // Returns all condos in a building
    public List<Condo> findAllCondosByBuildingId(Integer buildingId);
}
