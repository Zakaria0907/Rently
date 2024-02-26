package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;

import java.util.List;

public interface BuildingService {
	
	public Building save(Building building);
	
	public boolean exists(Building building);
	
	public List<Building> findAllByCompanyId(Integer companyId);
}
