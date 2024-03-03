package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;

import java.util.List;

public interface CondoService {

    public Condo save(Condo condo);
    
    public Condo update(CondoDto condoDto, Condo condo);
    
    public void delete(Condo condo);

    public boolean exists(Condo condo);
    
    public boolean exists(Integer buildingId);
    
    
    
    public Condo findById(Integer id);
    
    // Returns the number of condos in a building
    public Integer countCondosByBuildingId(Integer companyId);
    
    // Returns all condos in a building
    public List<Condo> findAllCondosByBuildingId(Integer buildingId);
}
