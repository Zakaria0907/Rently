package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.services.CondoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoRepository condoRepository;

    public Condo save(Condo condo) {
        return condoRepository.save(condo);
    }

    public boolean exists(Condo condo) {
        return condoRepository.existsById(condo.getId());
    }

}
