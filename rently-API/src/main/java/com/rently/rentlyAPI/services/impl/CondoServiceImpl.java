package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.services.CondoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoRepository condoRepository;
    
    @Override
    public Condo save(Condo condo) {
        return condoRepository.save(condo);
    }

    @Override
    public boolean exists(Condo condo) {
        return condoRepository.existsById(condo.getId());
    }
    
    @Override
    public Integer countCondosByBuildingId(Integer buildingId) {
        return condoRepository.countCondosById(buildingId);
    }
    
    @Override
    public List<Condo> findAllCondosByBuildingId(Integer buildingId) {
        return condoRepository.findAllCondosById(buildingId);
    }
}
