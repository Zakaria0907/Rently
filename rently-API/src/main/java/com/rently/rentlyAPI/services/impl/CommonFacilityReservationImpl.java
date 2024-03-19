package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CommonFacilityReservationRepository;
import com.rently.rentlyAPI.repository.CompanyRepository;
import com.rently.rentlyAPI.services.CommonFacilityReservationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommonFacilityReservationImpl implements CommonFacilityReservationService{

    private final CommonFacilityReservationRepository commonFacilityReservationRepository;

    @Override
    public CommonFacilityReservationDto createReservation(CommonFacilityReservationDto commonFacilityReservationDto) {
        //check the user does not already exist
        Optional<CommonFacilityReservation> existing = commonFacilityReservationRepository.findByOccupantId(commonFacilityReservationDto.getOccupant().getId(), commonFacilityReservationDto.getCommonFacility().getId());

        if (existing.isPresent() && existing.get().getOccupant().getId() != commonFacilityReservationDto.getOccupant().getId())
            throw new OperationNonPermittedException("There is already a reservation at the given time slot at the name of " + commonFacilityReservationDto.getOccupant().getFirstName());

        CommonFacilityReservation commonFacilityReservation = commonFacilityReservationDto.toEntity(commonFacilityReservationDto);
        CommonFacilityReservation savedReservation = commonFacilityReservationRepository.save(commonFacilityReservation);
        return commonFacilityReservationDto.fromEntity(savedReservation);
    }

    @Override
    public CommonFacilityReservationDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID) {
        // Find the CompanyAdmin Entity by its ID
        CommonFacilityReservation companyAdminToUpdate = commonFacilityReservationRepository.findByOccupantId(occupantID, commonFacilityID)
                .orElseThrow(() -> new EntityNotFoundException("Occupant with ID " + occupantID + " not found");

        // Update the the reservation details if present

        // Save the updated CompanyAdmin
        CommonFacilityReservation updatedReservation = commonFacilityReservationRepository.save(companyAdminToUpdate);

        return commonFacilityReservationDto.fromEntity(updatedReservation);
    }

    @Override
    public CommonFacilityReservationDto getReservation(Integer occupantID, Integer commonFacilityID) {
        return null;
    }

    @Override
    public String deletePublicUserById(Integer occupantID, Integer commonFacilityID) {
        return null;
    }
}
