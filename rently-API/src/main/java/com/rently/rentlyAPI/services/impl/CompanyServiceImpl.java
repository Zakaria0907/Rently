package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.security.Role;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.CondoService;
import com.rently.rentlyAPI.services.UserService;
import com.rently.rentlyAPI.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CondoService condoService;
    private final UserRepository userRepository;
    private final CondoRepository condoRepository;
    private final ObjectsValidator<Object> validator;
    public CondoDto createCondoForCompanyById(Integer companyId, CondoDto condoDto) {
        // Validate the condoDto
        validator.validate(condoDto);
        
        condoDto.setUserId(companyId);
        // Retrieve the User entity from the database using userId
        User user = null;
        if (condoDto.getUserId() != null) {
            user = userRepository.findById(condoDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with ID " + condoDto.getUserId() + " not found"));
        }

        // Set the user in the condo entity
        Condo condoEntity = CondoDto.toEntity(condoDto);
        condoEntity.setUser(user);

        // Save the condo entity
        CondoDto condo = CondoDto.fromEntity(condoService.save(condoEntity));

        return condo;
    }
}
