package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.Condo;

public interface CondoService {

    public Condo save(Condo condo);

    public boolean exists(Condo condo);
}
