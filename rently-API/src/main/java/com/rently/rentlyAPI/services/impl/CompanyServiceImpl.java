package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.CondoService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CondoService condoService;

    public CondoDto createCondo(CondoDto condoDto) {
        //check if condo already exists
        //if condo exists, throw OperationNonPermittedException
        if (condoService.exists(CondoDto.toEntity(condoDto))) {
            throw new DataIntegrityViolationException("Condo " + condoDto.getName() + " With id " + condoDto.getId() + " already exists");
        }

        return CondoDto.fromEntity(condoService.save(CondoDto.toEntity(condoDto)));
    }


}
