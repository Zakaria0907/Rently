package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CommonFacilityReservationRepository;
import com.rently.rentlyAPI.services.CommonFacilityReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommonFacilityReservationServiceImpl implements CommonFacilityReservationService {

    private final CommonFacilityReservationRepository commonFacilityReservationRepository;

    @Override
    public CommonFacilityReservationDto createReservation(CommonFacilityReservationDto commonFacilityReservationDto) {
        //check if there is already a reservation at the given time slot for the given user
        Optional<CommonFacilityReservation> existing = commonFacilityReservationRepository.findByOccupantIdAndDate(commonFacilityReservationDto.getOccupantId(), commonFacilityReservationDto.getReservationDate());

        if (existing.isPresent()) { /*&& existing.get().getOccupant().getId().equals(commonFacilityReservationDto.getOccupantId()))*/
            throw new OperationNonPermittedException("There is already a reservation at the given time slot for occupant with id " + commonFacilityReservationDto.getOccupantId());
        }
        CommonFacilityReservation commonFacilityReservation = CommonFacilityReservationDto.toEntity(commonFacilityReservationDto);
        // set the company, commonFacility, and occupant

        CommonFacilityReservation savedReservation = commonFacilityReservationRepository.save(commonFacilityReservation);
        return CommonFacilityReservationDto.fromEntity(savedReservation);
    }

    @Override
    public CommonFacilityReservationDto updateReservation(CommonFacilityReservationDto commonFacilityReservationDto, Integer occupantID, Integer commonFacilityID) {
//        // Find the CompanyAdmin Entity by its ID
//        CommonFacilityReservation companyAdminToUpdate = commonFacilityReservationRepository.findByOccupantId(occupantID, commonFacilityID)
//                .orElseThrow(() -> new EntityNotFoundException("Occupant with ID " + occupantID + " not found"));
//
//        // Update the the reservation details if present
//
//        // Save the updated CompanyAdmin
//        CommonFacilityReservation updatedReservation = commonFacilityReservationRepository.save(companyAdminToUpdate);
//
//        return commonFacilityReservationDto.fromEntity(updatedReservation);
        return null;
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
